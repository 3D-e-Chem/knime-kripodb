# KripoDB KNIME nodes

[KNIME](http://www.knime.org) nodes for KripoDB (https://github.com/3D-e-Chem/kripodb).

[![Build Status](https://travis-ci.org/3D-e-Chem/knime-kripodb.svg?branch=master)](https://travis-ci.org/3D-e-Chem/knime-kripodb)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/bde4d072a1874e7abae252b1e46a9c3a)](https://www.codacy.com/app/3D-e-Chem/knime-kripodb?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=3D-e-Chem/knime-kripodb&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/bde4d072a1874e7abae252b1e46a9c3a)](https://www.codacy.com/app/3D-e-Chem/knime-kripodb?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=3D-e-Chem/knime-kripodb&amp;utm_campaign=Badge_Coverage)
[![DOI](https://zenodo.org/badge/19641/3D-e-Chem/knime-kripodb.svg)](https://zenodo.org/badge/latestdoi/19641/3D-e-Chem/knime-kripodb)

# Installation

Requirements:

* KNIME, https://www.knime.org
* KripoDB Python package & optional data files, https://github.com/3D-e-Chem/kripodb

Steps to get KripoDB nodes inside KNIME:

1. Goto Help > Install new software ... menu
2. Press add button
3. Fill text fields with `https://3d-e-chem.github.io/updates`
4. Select --all sites-- in work with pulldown
5. Open KNIME 3D-e-Chem Contributions folder
6. Select KripoDB
7. Install software & restart

# Usage

See a minimal example workflow at [examples/Knime-KripoDB-example.zip](examples/Knime-KripoDB-example.zip).
The workflow can be run by importing it into KNIME as an archive.

Other workflows using the KripoDB nodes can be found at https://github.com/3D-e-Chem/workflows

Make sure the Python used by KNIME is the same the Python with kripodb package installed.

# Development

Development requirements:

* Maven, https://maven.apache.org

Steps to get development environment setup:

1. Download KNIME SDK from https://www.knime.org/downloads/overview
2. Install/Extract/start KNIME SDK
3. Start SDK
4. Install m2e (Maven integration for Eclipse) + KNIME Python Integration + RDKit KNIME integration + KNIME Testing framework + 3D-e-Chem node category

    1. Goto Help > Install new software ...
    2. Make sure Update site is https://3d-e-chem.github.io/updates ,  http://update.knime.org/analytics-platform/3.1 and http://update.knime.org/community-contributions/trusted/3.1 are in the pull down list otherwise add it
    3. Select --all sites-- in work with pulldown
    4. Select m2e (Maven integration for Eclipse)
    5. Select `RDKit KNIME integration`
    6. Select `Abstract Python wrapper KNIME node and helpers`
    7. Select `Test Knime workflows from a Junit test`
    8. Select `Splash & node category for 3D-e-Chem KNIME nodes`
    9. Install software & restart

5. Import this repo as an Existing Maven project

During import the Tycho Eclipse providers must be installed.

## Build

```
mvn package
```

Jar has been made in `plugin/target` directory.
An Eclipse update site will be made in `p2/target/repository` repository.

## Tests

```
mvn verify
```

Tests in `tests` module will have been run with results in `test/target/surefire-reports` directory.
There are unit tests and workflow tests both are executed in the KNIME eclipse application.
See https://github.com/3D-e-Chem/knime-testflow for more information about workflow tests.

## New release

1. Update versions in pom files with `mvn org.eclipse.tycho:tycho-versions-plugin:set-version -DnewVersion=<version>` command.
2. Manually update version of "source" feature in `p2/category.xml` file.
3. Commit and push changes
4. Create package with `mvn package`, will create update site in `p2/target/repository`
5. Test node by installing it from local update site
4. Append new release to 3D-e-Chem update site
  1. Make clone of https://github.com/3D-e-Chem/3D-e-Chem.github.io repo
  2. Append release to 3D-e-Chem update site with `mvn install -Dtarget.update.site=<3D-e-Chem repo/updates>`
5. Commit and push changes in this repo and 3D-e-Chem.github.io repo
6. Create a Github release
7. Update Zenodo entry
  1. Fix authors
  2. Fix license
  3. To Related/alternate identifiers section add http://dx.doi.org/10.1186/1758-2946-6-S1-O26 as `is cited by this upload` entry.
8. Make nodes available to 3D-e-Chem KNIME feature by following steps at https://github.com/3D-e-Chem/knime-node-collection#new-release

# Create stub recordings for integration tests

The test workflow are tested against a mocked web server and not the actual http://3d-e-chem.vu-compmedchem.nl/kripodb site.
The mock server is called [WireMock](http://WireMock.org/) and normally gives empty responses.
To have WireMock server return filled responses, stubs stored in `tests/src/test/resources/` directory must be provided.
The stubs can be recorded by starting a WireMock server in recording mode by:
```
java -jar tests/lib/wiremock-standalone-2.5.0.jar --proxy-all="http://3d-e-chem.vu-compmedchem.nl/" \
--port=8089 --record-mappings --verbose --root-dir=tests/src/test/resources/
java -jar tests/lib/wiremock-standalone-2.5.0.jar --proxy-all="http://localhost:8084/" \
--port=8089 --record-mappings --verbose --root-dir=tests/src/test/resources/
```

Then in a KNIME workflow in the KripoDB nodes set the base path to http://localhost:8089.
Executing the workflow will fetch data from http://3d-e-chem.vu-compmedchem.nl/kripodb  via the WireMock server and cause new stubs to be recorded in the `tests/src/test/resources/` directory.

To run the test workflows from inside KNIME desktop enviroment start the WireMock server in mock mode by:

```
java -jar tests/lib/wiremock-standalone-2.5.0.jar --port=8089 --verbose --root-dir=tests/src/test/resources/
```
Then import the test workflows in `tests/src/knime/` directory, select the workflow in the KNIME explorer and in the context menu (right-click) select `Run as workflow test`.

