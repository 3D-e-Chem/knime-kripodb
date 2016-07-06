package nl.esciencecenter.e3dchem.kripodb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.knime.base.node.util.exttool.ExtToolOutputNodeModel;
import org.knime.core.data.DataTableSpec;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;
import org.knime.core.node.port.PortType;
import org.knime.core.node.workflow.FlowVariable;
import org.knime.core.node.workflow.FlowVariable.Type;
import org.knime.python.kernel.PythonKernel;

public abstract class PythonNodeModel<Config extends PythonNodeConfig> extends ExtToolOutputNodeModel {
    Config m_config = createConfig();
    protected String python_code_filename;
    protected List<String> required_python_packages = Arrays.asList();

    public PythonNodeModel(final PortType[] inPortTypes, final PortType[] outPortTypes) {
        super(inPortTypes, outPortTypes);
    }

    protected abstract Config createConfig();

    protected final Config getConfig() {
        return m_config;
    }

    protected BufferedDataTable[] execute(BufferedDataTable[] inData, ExecutionContext exec) throws Exception {
        // Below has been copied from Knime Python node source code and
        // adjusted.
        PythonKernel kernel = new PythonKernel();
        BufferedDataTable table = null;
        try {
            kernel.putFlowVariables(Config.getVariableNames().getFlowVariables(), getAvailableFlowVariables().values());
            kernel.putDataTable(Config.getVariableNames().getInputTables()[0], inData[0], exec.createSubProgress(0.3));
            kernel.putFlowVariables(Config.getOptionsName(), m_config.toFlowVariables());
            String[] output = kernel.execute(getPythonCode(), exec);
            setExternalOutput(new LinkedList<String>(Arrays.asList(output[0].split("\n"))));
            setExternalErrorOutput(new LinkedList<String>(Arrays.asList(output[1].split("\n"))));
            exec.createSubProgress(0.4).setProgress(1);
            Collection<FlowVariable> variables = kernel.getFlowVariables(Config.getVariableNames().getFlowVariables());
            table = kernel.getDataTable(Config.getVariableNames().getOutputTables()[0], exec, exec.createSubProgress(0.3));
            addNewVariables(variables);
        } finally {
            kernel.close();
        }

        return new BufferedDataTable[] { table };
    }

    /**
     * Push new variables to the stack.
     * 
     * Only pushes new variables to the stack if they are new or changed in type or value.
     * 
     * @param newVariables
     *            The flow variables to push
     */
    protected void addNewVariables(Collection<FlowVariable> newVariables) {
        // Below has been copied from Knime Python node source code and
        // adjusted.
        Map<String, FlowVariable> flowVariables = getAvailableFlowVariables();
        for (FlowVariable variable : newVariables) {
            // Only push if variable is new or has changed type or value
            boolean push = true;
            if (flowVariables.containsKey(variable.getName())) {
                // Old variable with the name exists
                FlowVariable oldVariable = flowVariables.get(variable.getName());
                if (oldVariable.getType().equals(variable.getType())) {
                    // Old variable has the same type
                    if (variable.getType().equals(Type.INTEGER)) {
                        if (oldVariable.getIntValue() == variable.getIntValue()) {
                            // Old variable has the same value
                            push = false;
                        }
                    } else if (variable.getType().equals(Type.DOUBLE)) {
                        if (new Double(oldVariable.getDoubleValue()).equals(new Double(variable.getDoubleValue()))) {
                            // Old variable has the same value
                            push = false;
                        }
                    } else if (variable.getType().equals(Type.STRING)) {
                        if (oldVariable.getStringValue().equals(variable.getStringValue())) {
                            // Old variable has the same value
                            push = false;
                        }
                    }
                }
            }
            if (push) {
                if (variable.getType().equals(Type.INTEGER)) {
                    pushFlowVariableInt(variable.getName(), variable.getIntValue());
                } else if (variable.getType().equals(Type.DOUBLE)) {
                    pushFlowVariableDouble(variable.getName(), variable.getDoubleValue());
                } else if (variable.getType().equals(Type.STRING)) {
                    pushFlowVariableString(variable.getName(), variable.getStringValue());
                }
            }
        }
    }

    protected String getPythonCode() {
        InputStream inputStream = getClass().getResourceAsStream(python_code_filename);
        String result = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveSettingsTo(final NodeSettingsWO settings) {
        m_config.saveTo(settings);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void validateSettings(final NodeSettingsRO settings) throws InvalidSettingsException {
        Config config = createConfig();
        config.loadFrom(settings);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void loadValidatedSettingsFrom(final NodeSettingsRO settings) throws InvalidSettingsException {
        Config config = createConfig();
        config.loadFrom(settings);
        m_config = config;
    }

    /**
     * The output spec returned by the configure() method.
     * 
     * @param inSpecs
     * @return
     */
    protected DataTableSpec[] getOutputSpecs(DataTableSpec[] inSpecs) {
        return new DataTableSpec[] { null };
    }

    @Override
    protected DataTableSpec[] configure(final DataTableSpec[] inSpecs) throws InvalidSettingsException {
        validPython();
        return getOutputSpecs(inSpecs);
    }

    /**
     * Checks if Python packages in `required_python_packages` can be imported.
     * 
     * @throws InvalidSettingsException
     */
    protected void validPython() throws InvalidSettingsException {
        if (required_python_packages.isEmpty()) {
            return;
        }
        String pythonCommand = org.knime.python.Activator.getPythonCommand();
        String program = required_python_packages.stream().map(p -> "import " + p).collect(Collectors.joining(";"));
        ProcessBuilder pb = new ProcessBuilder(pythonCommand, "-c", program);
        try {
            Process process = pb.start();
            int exitValue = process.waitFor();
            if (exitValue != 0) {
                String msg = "Missing ";
                msg += required_python_packages.stream().map(p -> "'" + p + "'").collect(Collectors.joining(" or "));
                msg += " Python package(s), please install or correct Python executable";
                throw new InvalidSettingsException(msg);
            }
        } catch (IOException | InterruptedException e) {
            throw new InvalidSettingsException("Failed to check required Python packages", e);
        }
    }
}