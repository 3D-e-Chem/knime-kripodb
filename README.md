# KripoDB Knime Python templates

[![Build Status](https://travis-ci.org/3D-e-Chem/knime-kripodb.svg?branch=master)](https://travis-ci.org/3D-e-Chem/knime-kripodb)


# Installation

Requirements:

* KNIME, https://www.knime.org
* KripoDB Python package, https://github.com/3D-e-Chem/kripodb

Steps to get KripoDB Python templates inside KNIME:

1. Goto Help > Install new software ... menu
2. Press add button
3. Fill text fields with `https://3d-e-chem.github.io/updates`
4. Select --all sites-- in work with pulldown
5. Open KNIME 3D-e-Chem Contributions folder
6. Select KripoDB
7. Install software & restart

# Usage

See example workflow at [examples/Knime-KripoDB-example.zip](examples/Knime-KripoDB-example.zip).

It can be run by importing it into Knime. 
Make sure the Python used by Knime is the same the Python with kripodb package installed.

# Build

```
mvn verify
```

Jar has been made in `/target` folder.
An Eclipse update site will be made in `p2/target/repository` repository.

# Development

Steps to get development environment setup:

1. Download KNIME SDK from https://www.knime.org/downloads/overview
2. Install/Extract/start KNIME SDK
3. Start SDK
4. Install m2e (Maven integration for Eclipse) + Knime Python Integration

    1. Goto Help > Install new software ...
    2. Make sure Update site is http://update.knime.org/analytics-platform/3.1 in the pull down list otherwise add it
    3. Select --all sites-- in work with pulldown
    4. Select m2e (Maven integration for Eclipse)
    5. Select `Knime Python Integration`
    6. Install software & restart

5. Import this repo as an Existing Maven project

During import the Tycho Eclipse providers must be installed.

# New release

1. Update versions in pom files with `mvn org.eclipse.tycho:tycho-versions-plugin:set-version -DnewVersion=<version>` command.
2. Manually update version of "source" feature in `p2/category.xml` file.
3. Commit and push changes
3. Create package with `mvn package`, will create update site in `p2/target/repository`
4. Append new release to 3D-e-Chem update site
  1. Make clone of https://github.com/3D-e-Chem/3D-e-Chem.github.io repo
  2. Append release to 3D-e-Chem update site with `mvn install -Dtarget.update.site=<3D-e-Chem repo/updates>`
5. Commit and push changes in this repo and 3D-e-Chem.github.io repo

## Offline Knime update site

If Knime update site can not be contacted then use a local version.

1. Download zip of update site from https://www.knime.org/downloads/update
2. Unzip it
3. To maven commands add `-Dknime.update.site=file://-Dknime.update.site=file://<path to update site directory>`