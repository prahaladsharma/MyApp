
# Android Clean Architecture

A demo android app showcasing Clean Architecture, written in Kotlin and featuring Jetpack Compose for modern, declarative UIs. 💯🎞


# Features
1. **List of List Member**: The app can be accessed list of Game of Thrones House List Member.
2. **Details Screen**: Allows users to show the detasils of every house members.


# Clean Architecture

The core principles of the clean approach can be summarized as followed:

#### 1. The application code is separated into layers.

These layers define the separation of concerns inside the code base.

#### 2. The layers follow a strict dependency rule.

Each layer can only interact with the layers below it.

#### 3. As we move toward the bottom layer — the code becomes generic.

The bottom layers dictate policies and rules, and the upper layers dictate implementation details such as the database, networking manager, and UI.

<p align="center">
<img src="https://raw.githubusercontent.com/AliAsadi/Android-Clean-Architecture/master/screenshot/architecture0.png">
</p>

# Architecture Layers

The application consists of three layers:

The domain layer, the data layer, and the presentation layer.

## Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
- [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) - A live data replacement.
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Modern toolkit for building native UIs.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
  - [SavedStateHandle](https://developer.android.com/reference/androidx/lifecycle/SavedStateHandle) - A handle to saved state passed down to androidx.lifecycle.ViewModel.
  - [Navigation Components](https://developer.android.com/guide/navigation/navigation-getting-started) - Navigate fragments easier.
  
- [Dependency Injection](https://developer.android.com/training/dependency-injection)
  - [Hilt](https://dagger.dev/hilt) - Easier way to incorporate Dagger DI into Android application.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Mockito](https://github.com/mockito/mockito) - For Mocking and Unit Testing
