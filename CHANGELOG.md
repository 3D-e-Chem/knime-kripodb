# Change log

## Unreleased

## 2.0.1 - 2016-07-11

### Changed

* Moved PythonWrapper classes to own repo (https://github.com/3D-e-Chem/knime-python-wrapper)

## 2.0.0 - 2016-07-06

Version <2.0.0 used Python templates which could be selected as source code and adjusted in a text area in the `Python script` node.
Version >= 2.0.0 uses KripoDB Knime node which have there own dialog with combo boxes and file pickers.

### Added

* Workflow tests
* Run workflow tests on Travis-CI
* Codacy badge
* Check that Python packages are available before executing

### Changed

* Use KripoDB Knime node instead of Python node with a KripoDB template (#3)

## 1.0.3 - 2016-06-21

### Added

* bioisosteric replacement workflow

### Fixed

* Example 'Add fragment for hits' node returns too many rows (#2)

## 1.0.2 - 2016-05-25

### Added

* Use webservice in template and example (#1)

## 1.0.1 - 2016-04-18

### Added

* Distance matrix can be local file or kripodb webservice base url.

### Removed

* DOI for this repo, see https://github.com/3D-e-Chem/kripodb for DOI.

## 1.0.0 - 2016-02-12

### Added

* Python templates to use KripoDB package
* Example workflow on Github repo.
