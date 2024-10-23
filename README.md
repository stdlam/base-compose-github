# Architecture

This is a basic code that uses Clean Architecture & MVVM & Components

-   [Android architecture components](https://developer.android.com/topic/libraries/architecture/), part of Android Jetpack for give to project a robust design, testable and maintainable.
-   Pattern [Model-View-ViewModel](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) (MVVM) facilitating a separation of development of the graphical user interface.
-   [S.O.L.I.D](https://en.wikipedia.org/wiki/SOLID) design principles intended to make software designs more understandable, flexible and maintainable.

# Modules

Modules are collection of source files and build settings that allow you to divide a project into discrete units of functionality

-   `:app` depends on `:internal:core`, `:internal:data` and `:internal:domain`.
-   `:internal:data` modules depends on `:internal:domain`.
-   `:internal:core` only depends for possible utils on `:libraries`.
-   `:libraries` don’t have any dependency.
<br />
<br />
<br />

# Implementation

### Data-Flow
![Structure](screenshots/data-flow.png "Data flow")

### Work-Flow
![Structure](screenshots/work-flow.png "Work flow")

#### Domain Layer
- Contains business model 
- Contains business RULEs
- Repository interface adapt 

#### Data Layer
- Implementation Repository
- Executor API data
- Storage data to local: Share preferences, database, external storage 
- Mapper data model to domain model
- Contains data service, third party data service  

#### Presentation Layer
- View (Activity/Fragment/Layout) Adapt data to view 
- Validate/Submit data input from view via UseCase

# Building

- Work from Android Studio `Electric Eel | 2022.1.1` and above
- Java SDK `17`
- Kotlin `1.8.21`
- Gradle `7.4.2`


-   [Ktlint](https://github.com/pinterest/ktlint) - an anti-bikeshedding Kotlin linter with built-in formatter.
-   [Detekt](https://github.com/arturbosch/detekt) - a static code analysis tool for the Kotlin programming language.
-   [SafeArgs](https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args) - generates simple object and builder classes for type-safe navigation and access to any associated arguments.
-   and more...
