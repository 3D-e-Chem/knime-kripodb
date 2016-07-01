package nl.esciencecenter.e3dchem.kripodb.fragments;

import java.io.File;

import org.knime.core.data.DataTableSpec;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.port.PortType;

import nl.esciencecenter.e3dchem.kripodb.PythonNodeModel;

/**
 * This is the model implementation of FragmentBySimilarity.
 *
 */
public class FragmentByIdModel extends PythonNodeModel<FragmentsByIdConfig> {
    public FragmentByIdModel() {
        super(new PortType[] { BufferedDataTable.TYPE }, new PortType[] { BufferedDataTable.TYPE });
        python_code_filename = "fragment_by_id.py";
    }

    @Override
    protected FragmentsByIdConfig createConfig() {
        return new FragmentsByIdConfig();
    }

    // TODO mol column is not the same in configure() and execute(), Knime
    // returns:
    // ERROR KNIME-Worker-0 Fragment information DataSpec generated by configure
    // does not match spec after execution.

    // @Override
    // protected DataTableSpec[] getOutputSpecs(DataTableSpec[] inSpecs) {
    // DataTableSpec idSpec = inSpecs[0];
    // FragmentsByIdConfig config = getConfig();
    // String prefix = config.getPrefix().getStringValue();
    // DataTableSpecCreator result = new DataTableSpecCreator(idSpec);
    // result.addColumns(new DataColumnSpecCreator(prefix + "atom_codes",
    // StringCell.TYPE).createSpec(),
    // new DataColumnSpecCreator(prefix + "ec_number",
    // StringCell.TYPE).createSpec());
    // // Only add frag_id column when it is not in input table, otherwise:
    // // Configure failed (IllegalArgumentException): Column "frag_id" already
    // // contained at index 0
    // if (idSpec.findColumnIndex(prefix + "frag_id") == -1) {
    // result.addColumns(new DataColumnSpecCreator(prefix + "frag_id",
    // StringCell.TYPE).createSpec());
    // }
    // result.addColumns(new DataColumnSpecCreator(prefix + "frag_nr",
    // IntCell.TYPE).createSpec(),
    // new DataColumnSpecCreator(prefix + "hash_code",
    // StringCell.TYPE).createSpec(),
    // new DataColumnSpecCreator(prefix + "het_chain",
    // StringCell.TYPE).createSpec(),
    // new DataColumnSpecCreator(prefix + "het_code",
    // StringCell.TYPE).createSpec(),
    // new DataColumnSpecCreator(prefix + "het_seq_nr",
    // IntCell.TYPE).createSpec(),
    // new DataColumnSpecCreator(prefix + "mol",
    // DataType.getType(RDKitMolCell2.class)).createSpec(),
    // new DataColumnSpecCreator(prefix + "nr_r_groups",
    // StringCell.TYPE).createSpec());
    // if (idSpec.findColumnIndex(prefix + "pdb_code") == -1) {
    // result.addColumns(new DataColumnSpecCreator(prefix + "pdb_code",
    // StringCell.TYPE).createSpec());
    // }
    // result.addColumns(new DataColumnSpecCreator(prefix + "pdb_title",
    // StringCell.TYPE).createSpec(),
    // new DataColumnSpecCreator(prefix + "prot_chain",
    // StringCell.TYPE).createSpec(),
    // new DataColumnSpecCreator(prefix + "prot_name",
    // StringCell.TYPE).createSpec(),
    // new DataColumnSpecCreator(prefix + "rowid", IntCell.TYPE).createSpec(),
    // new DataColumnSpecCreator(prefix + "smiles",
    // StringCell.TYPE).createSpec(),
    // new DataColumnSpecCreator(prefix + "uniprot_acc",
    // StringCell.TYPE).createSpec(),
    // new DataColumnSpecCreator(prefix + "uniprot_name",
    // StringCell.TYPE).createSpec());
    //
    // return new DataTableSpec[] { result.createSpec() };
    // }

    @Override
    protected DataTableSpec[] configure(final DataTableSpec[] inSpecs) throws InvalidSettingsException {
        // TODO test if kripodb Python package is installed
        FragmentsByIdConfig config = getConfig();

        String idColumn = config.getIdColumn().getStringValue();
        int idColumnIndex = inSpecs[0].findColumnIndex(idColumn);
        if (idColumnIndex < 0) {
            throw new InvalidSettingsException("No valid identifier column selected, require a String column");
        }

        String fragmentsdb_fn = config.getFragmentsDB().getStringValue();
        if (fragmentsdb_fn == "" || fragmentsdb_fn == null) {
            throw new InvalidSettingsException("Require a fragments database file");
        } else {
            File fragmentsdb_file = new File(fragmentsdb_fn);
            if (!fragmentsdb_file.canRead()) {
                throw new InvalidSettingsException("Unable to read fragments database file");
            }
        }

        return super.configure(inSpecs);
    }

}
