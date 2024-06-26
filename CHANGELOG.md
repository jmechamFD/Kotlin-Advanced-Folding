# Kotlin-Advanced-Folding Changelog

## [0.0.5] - 2024-06-26

### Added
- Adds option to automatically fold method calls inside an array or collection

### Fixed
- Fixed issue where if you fold the parameter list of a method definition, you were unable to fold the body.

## [0.0.4] - 2024-06-13

### Added
- Add Code Folding for Method Definitions
- Adds configuration settings to fold the following by default: Annotations, Method Calls/Definitions and Collections
- Adds configuration settings to enable and disable showing of the first parameter/element of the folded code

### Fixed
- Only folds regions between Parentheses or Brackets instead of folding the entire element

## [0.0.3] - 2024-05-03

### Fixed
- Updated README.md


## [0.0.2] - 2024-05-02

### Added

- Added Folding for Annotations
- Added Folding for Arrays/Collections
- Added Folding for Method Calls
- Initial scaffold created from [IntelliJ Platform Plugin Template](https://github.com/JetBrains/intellij-platform-plugin-template)

[Unreleased]: https://github.com/jmechamFD/Kotlin-Advanced-Folding/compare/v0.0.1...HEAD
[0.0.1]: https://github.com/jmechamFD/Kotlin-Advanced-Folding/commits/v0.0.1
