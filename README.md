# Android Gallery App Assessment

This Android Gallery App serves as an assessment project to evaluate your proficiency in utilizing modern Android development technologies. The app focuses on building a gallery interface with two main screens: one for displaying available media buckets and another for showcasing the media contained in each bucket. The project incorporates the following key technologies:

## Technologies Used

- **Dagger Hilt**: Utilize Dagger Hilt for dependency injection, simplifying the process of providing and managing dependencies in your app.

- **MVI (Model-View-Intent)**: Adopt the MVI architectural pattern to ensure a unidirectional flow of data and improve the predictability of state changes.

- **Clean Architecture**: Implement Clean Architecture principles to promote separation of concerns, making your codebase modular and maintainable.

- **Coroutine**: Leverage Kotlin Coroutines for asynchronous programming, ensuring smooth and efficient handling of background tasks.

- **DDD (Domain-Driven Design)**: Apply DDD concepts to model your app's domain and business logic in a way that aligns with the problem space.

- **Kotlin**: Develop the app using Kotlin, taking advantage of its concise syntax and modern language features.

- **Coil**: Integrate Coil for efficient image loading and caching, ensuring a smooth media viewing experience.

- **Compose**: Utilize Jetpack Compose for building the user interface, taking advantage of the declarative UI approach and simplified UI development.

- **Dark Mode**: Implement a dark mode feature to enhance the user experience and provide visual customization options.

## Screens

### 1. Media Buckets Screen

- Display a list of available media buckets, including "All Photos" and "All Videos."
- Utilize the MVI pattern to manage the state of the screen.
- Employ Dagger Hilt for dependency injection to achieve a modular and testable codebase.

### 2. Media Contents Screen

- Display the media contents within each selected bucket.
- Implement a clean and intuitive UI using Jetpack Compose.
- Apply the MVI pattern to handle the state of the media contents screen.
- Leverage Kotlin Coroutines for asynchronous tasks such as fetching and displaying media.

## Getting Started

1. Clone the repository.
2. Open the project in Android Studio.
3. Build and run the app on an Android emulator or physical device.
