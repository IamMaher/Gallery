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

- **Grid and Linear Toggle:**: Implement a toggle functionality to switch between grid and linear layouts for media display.

- **Persistent Setting by DataStore:**: Use DataStore to persistently store and retrieve user settings, ensuring a seamless experience across app sessions.


## Screens

![Album](https://github.com/IamMaher/Gallery/assets/15931456/1813bd13-7dab-4112-8667-c11858a5304b)
![media_content_grid](https://github.com/IamMaher/Gallery/assets/15931456/a33a0031-eb51-4f7d-ace5-3706e8c4b480)
![media_content_linear](https://github.com/IamMaher/Gallery/assets/15931456/241f3728-6195-4ec6-ad1c-131db11e52e5)
![darkmode](https://github.com/IamMaher/Gallery/assets/15931456/826c962e-ee2c-412f-857a-bd8d417db57c)


### 1. Media Buckets Screen

- Display a list of available media buckets, including "All Photos" and "All Videos."
- Utilize Dagger Hilt for dependency injection.
- Implement MVI architecture to manage the UI state.
- Apply Clean Architecture principles for separation of concerns.

### 2. Media Contents Screen

- Show the media contained in each selected bucket.
- Utilize Coil for efficient image loading.
- Implement a toggle to switch between grid and linear layouts for media display.
- Incorporate Dark Mode for a personalized visual experience.
- Utilize DataStore to persistently store and retrieve user settings.

## Getting Started

1. Clone the repository.
2. Open the project in Android Studio.
3. Build and run the app on an Android emulator or physical device.
