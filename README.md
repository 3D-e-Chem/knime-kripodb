# KripoDB KNIME nodes

[KNIME](http://www.knime.org) nodes for KripoDB (https://github.com/3D-e-Chem/kripodb).

[![Build Status](https://travis-ci.org/3D-e-Chem/knime-kripodb.svg?branch=master)](https://travis-ci.org/3D-e-Chem/knime-kripodb)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/bde4d072a1874e7abae252b1e46a9c3a)](https://www.codacy.com/app/3D-e-Chem/knime-kripodb?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=3D-e-Chem/knime-kripodb&amp;utm_campaign=Badge_Grade)
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

See bioisosteric replacement workflow at [examples/KRIPO_bioisosteric_replacement_workflow.zip](examples/KRIPO_bioisosteric_replacement_workflow.zip).

Or see a minimal example workflow at [examples/Knime-KripoDB-example.zip](examples/Knime-KripoDB-example.zip).

Both workflows can be run by importing it into KNIME.
Make sure the Python used by KNIME is the same the Python with kripodb package installed.

# Development

Development requirements:

* Maven, https://maven.apache.org

Steps to get development environment setup:

1. Download KNIME SDK from https://www.knime.org/downloads/overview
2. Install/Extract/start KNIME SDK
3. Start SDK
4. Install m2e (Maven integration for Eclipse) + KNIME Python Integration + RDKit KNIME integration + KNIME Testing framework

    1. Goto Help > Install new software ...
    2. Make sure Update site is http://update.knime.org/analytics-platform/3.1 and http://update.knime.org/community-contributions/trusted/3.1 are in the pull down list otherwise add it
    3. Select --all sites-- in work with pulldown
    4. Select m2e (Maven integration for Eclipse)
    5. Select `KNIME Python Integration`
    6. Select `RDKit KNIME integration`
    7. Select `KNIME Testing framework`
    8. Install software & restart

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
3. Create package with `mvn package`, will create update site in `p2/target/repository`
4. Append new release to 3D-e-Chem update site
  1. Make clone of https://github.com/3D-e-Chem/3D-e-Chem.github.io repo
  2. Append release to 3D-e-Chem update site with `mvn install -Dtarget.update.site=<3D-e-Chem repo/updates>`
5. Commit and push changes in this repo and 3D-e-Chem.github.io repo
