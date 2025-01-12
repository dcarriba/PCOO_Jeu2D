# PCOO_Jeu2D

by Daniel Carriba Nosrati

## General

This project is an RPG game made in Java using [libGDX](https://libgdx.com/).

More information about this project can be found in : `Rapport_PCOO_Jeu2D_Daniel_Carriba_Nosrati.pdf`

## GitHub Repository

To access the GitHub repository :

https://github.com/dcarriba/PCOO_Jeu2D

## Download Game

Download the executable jar of the game in the [Releases](https://github.com/dcarriba/PCOO_Jeu2D/releases) section of the GitHub repository.

Requirements to run the game : Java 11 or later (minimum), Java 21 (recommended) [[download Java 21 here](https://www.oracle.com/fr/java/technologies/downloads/#java21)].

## Play the Game

Once in the game, use the following keys to play :

- `Up Arrow`: to go up.
- `Right Arrow`: to go right.
- `Down Arrow`: to go down.
- `Left Arrow`: to go left.
- `Left Shift`: to sprint.
- `Space Bar`: to kill an enemy.

## Clone Repository

To clone this repository go to an empty directory and use the following command :

`git clone https://github.com/dcarriba/PCOO_Jeu2D.git`

## Instructions to compile and execute

#### Requirements :

- Java 11 or later (minimum), Java 21 (recommended) [[download Java 21 here](https://www.oracle.com/fr/java/technologies/downloads/#java21)].
- [libGDX 1.13.0.0](https://github.com/libgdx/gdx-liftoff/releases/tag/v1.13.0.0) was used for this project.
- This project uses [Gradle](https://gradle.org/) to manage dependencies. The Gradle wrapper is included, so you can run Gradle tasks using `./gradlew` or `./gradlew.bat` commands.
- Make sure that the `gradlew` and `gradlew.bat` files have the rights to be executed.

#### How to compile :

To compile the project use the following command :

`./gradlew build`

#### How to execute :

To start the application use the following command :

`./gradlew run`

#### Other useful commands :

- `./gradlew lwjgl3:run`: starts the application.
- `./gradlew jar` or `./gradlew lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/libs/`.
- `./gradlew clean`: removes `build` folders, which store compiled classes and built archives.
- `./gradlew idea`: generates IntelliJ project data.
- `./gradlew eclipse`: generates Eclipse project data.
- `./gradlew cleanIdea`: removes IntelliJ project data.
- `./gradlew cleanEclipse`: removes Eclipse project data.

Note : for all previous commands you can use `./gradlew.bat` instead of `./gradlew`
