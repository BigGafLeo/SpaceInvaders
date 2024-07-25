# Space Invaders

This repository contains an implementation of the classic Space Invaders game developed for Android. The project includes game logic, data models, and various resources needed to run the game.

## Contents

- **app/**: Contains the main application code and resources.
  - **src/main/java/com/example/spaceinvaders/**: Java and Kotlin source files organized by functionality.
    - **dataModel**: Data models and database access objects (DAOs).
      - `AppDatabase.kt`
      - `Converters.kt`
      - `Player.kt`
      - `PlayerDao.kt`
    - **gameLogic**: Core game logic and mechanics.
      - `AlienManager.kt`
      - `Bullet.kt`
      - `BulletManager.kt`
      - `GameEngine.kt`
      - `GameObject.kt`
      - `Player.kt`
      - `PlayerManager.kt`
    - **ui**: User interface components.
      - `MainActivity.kt`
      - `GameView.kt`
  - **src/main/res/**: Application resources such as layouts, drawables, and values.
    - **layout**: XML layout files.
    - **drawable**: Image resources.
    - **mipmap**: Launcher icons.
    - **values**: Resource values (strings, colors, themes).
- **gradle/**: Gradle wrapper and properties for building the project.
- **.idea/**: IDE configuration files.

## Algorithms and Techniques Used

### Game Logic

- **AlienManager.kt**: Manages the behavior and movement of alien invaders.
- **BulletManager.kt**: Handles the creation, movement, and collision of bullets.
- **GameEngine.kt**: Central game engine managing game state, updates, and rendering.
- **PlayerManager.kt**: Manages player actions, movements, and interactions with game objects.

### Data Models

- **AppDatabase.kt**: Room database configuration.
- **Player.kt**: Data model representing a player.
- **PlayerDao.kt**: Data access object for player-related database operations.

## Project Structure

- **src/**: Contains the source code organized into different packages.
- **data/**: Contains datasets for testing.
- **lib/**: Contains external libraries.
- **wykresy/**: Contains result charts.
- **Sprawozdanie.pdf**: Detailed report and analysis of the project.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

