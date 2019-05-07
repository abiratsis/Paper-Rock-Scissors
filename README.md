# Paper-Rock-Scissors Game

The famous game played with the following rules:

- Paper beats (wraps) rock
- Rock beats (blunts) scissors
- Scissors beats (cuts) paper.

## Getting Started

Unzip Paper-Rock-Scissors.zip file somewhere in your local directory.

### Prerequisites

Java 8 should be install on your system.

### Builing the project

The Paper-Rock-Scissors game uses Gradle. In order to build the project open a command line and navigate to the unzipped folder from the previous step, then execute

```
gradlew clean build jar
```

This will build the project, run the tests and generate the corresponding Paper-Rock-Scissors-1.0.jar file.

## Running the tests

The application uses JUnit 5.3 for unit testing. In order to run the tests execute the next command from the root directory:

```
gradlew clean test
```

## Running the application

To run the application execute the next command from the Paper-Rock-Scissors\build\libs directory:

```
java -jar Paper-Rock-Scissors-1.0.jar com.gaming.prs.Main
```

## Built With

* [Java 8](https://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html)
* [Gradle 4.10](https://docs.gradle.org/4.10/release-notes.html)
* [IntelliJ](https://www.jetbrains.com/idea/download/#section=windows)

