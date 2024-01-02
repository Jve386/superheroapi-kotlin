# Superhero API 🦸 Android App

## Project Overview 


### Technologies Used 💻

- Language: Kotlin
- Android Frameworks: Android SDK, Android Jetpack
- UI Components: XML layout with Material Design components
- RecyclerView: Display of superhero information
- View Binding: Interaction with UI components
- Kotlin Coroutines: Handling of asynchronous programming tasks
- Libraries: Retrofit for API requests, Picasso for image loading and caching
- Version Control: Git
- Build Tool: Gradle
  

### Features ⚙️

- Utilizes the Superhero API to fetch detailed information about superheroes.
- Search functionality using SearchView and organized results with RecyclerView.
- Asynchronous programming using Kotlin Coroutines for API data retrieval.
- Progress Bar for visual feedback during superhero information fetching.
- ImageView scaleType for optimal and visually appealing display of superhero images.
- API Service Interface for structured API calls.
  

## Key Components 🛠️

### Adapters/Controllers:

#### SuperheroAdapter
- **Purpose:** Manages the display of superhero search results in a RecyclerView.
- **Responsibilities:**
  - Inflates superhero item views.
  - Binds superhero data to the item views.

#### SuperheroViewHolder
- **Purpose:** Handles the view representation for individual superheroes within the RecyclerView.
- **Responsibilities:**
  - Represents the layout and UI elements for a single superhero item.
  - Provides methods to bind superhero data to the UI.



### Models:

#### SuperHeroDataResponse
- **Purpose:** Represents the response from the Superhero API, including superhero details.
- **Responsibilities:**
  - Holds data about the response, such as whether it was successful.
  - Contains a list of superhero items.

#### SuperHeroDetailResponse
- **Purpose:** Represents detailed information about a superhero.
- **Responsibilities:**
  - Includes data such as name, powerstats, image, biography, and connections.



### View:

#### MainActivity
- **Purpose:** Displays a SearchView, RecyclerView, and handles user interactions for superhero search.
- **Responsibilities:**
  - Initiates and manages the UI components, including the SearchView and RecyclerView.
  - Listens for user input and triggers superhero searches.
  - Communicates with the SuperheroAdapter to display search results.

#### DetailSuperheroActivity
- **Purpose:** Displays detailed information about a selected superhero.
- **Responsibilities:**
  - Retrieves and displays detailed information about a specific superhero.
  - Utilizes the SuperheroAdapter and SuperheroViewHolder for UI consistency.



### Networking:

#### ApiService
- **Purpose:** Defines the API endpoints for fetching superhero data.
- **Responsibilities:**
  - Includes methods for searching superheroes and fetching detailed information about a specific superhero.



## Key Features 🚀

- Explore and access a comprehensive database of superheroes through the <a href="https://superheroapi.com/" rel="noopener noreferrer" target="_blank">SuperheroAPI</a>.
- Visual feedback with a Progress Bar during superhero information retrieval.
- Utilizes some Android features for a basic user-friendly experience.

## Media 📷

<p>
<img src="image/SuperheroAPI.gif" alt="SuperheroAPI" width="240" height="426"/>
<img src="image/SuperheroScreen.jpg" alt="SuperheroScreen" width="200" height="426"/>
</p>

## Additional Notes 📝

Feel free to contribute, open issues, or use this project as a learning resource for Android development and API integration. The project leverages modern Android development practices and provides insights into using View Binding, Coroutines, Retrofit, and Picasso. 

Happy coding 👌! 

## How to Use ✨

1. Clone the repository.
2. Open the project in Android Studio.
3. Make your own API at <a href="https://superheroapi.com/" rel="noopener noreferrer" target="_blank">SuperheroAPI</a>.
4. Add your personal Token to 'ApiService'.
5. Build and run on an Android device or emulator.