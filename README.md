# Micronaut Challenge - SelectCode

## Dependencies:
### MongoDB Compass (version 1.39.1):
- GUI for MongoDB, a NoSQL Database.
- Download: (https://www.mongodb.com/try/download/compass)

### Docker Desktop:
- Needed to run MongoDB
- Download: (https://www.docker.com/products/docker-desktop/)

## Starting the Application
### Starting MongoDB:
- Pull the MongoDB container: ``` docker pull mongo:latest ```
- Run the container: ``` docker run -d -p 27017:27017 –-name mongomarketplace mongo:latest ```
- Open MongoDB Compass
- Set URI to: ``` mongodb://localhost:27017 ```
- Click Connect
### Running the Application
- Clone the repository: ``` git clone https://github.com/dmnksto/marketplace ```
- Build the project: ``` ./gradlew build  ```
- Run the Application: ``` ./gradlew run ```
#### Alternatively:
- Open IntelliJ IDEA
- Select "File" -> "New" -> "Project from Version Control" -> "Git"
- Paste the repository URL: ``` https://github.com/dmnksto/marketplace ```
- Choose a directory to clone the repository
- Click ``` Clone ```
- Run ``` Application.kt ```

## Running Tests
- Build the project: ``` ./gradlew build  ```
- Run the Tests: ``` ./gradlew test ```
- Test results are available at ``` build/reports/tests/test/index.html ``` in the project directory.
#### Alternatively:
- Open the project in IntelliJ
- Run ``` ProductControllerTest.kt ```
- Run ``` OrderControllerTest.kt ```


## Micronaut 4.0.5 Documentation

- [User Guide](https://docs.micronaut.io/4.0.5/guide/index.html)
- [API Reference](https://docs.micronaut.io/4.0.5/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/4.0.5/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

- [Shadow Gradle Plugin](https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow)
- [Micronaut Gradle Plugin documentation](https://micronaut-projects.github.io/micronaut-gradle-plugin/latest/)
- [GraalVM Gradle Plugin documentation](https://graalvm.github.io/native-build-tools/latest/gradle-plugin.html)
## Feature data-mongodb documentation

- [Micronaut Data MongoDB documentation](https://micronaut-projects.github.io/micronaut-data/latest/guide/#mongo)

- [https://docs.mongodb.com](https://docs.mongodb.com)


## Feature mongo-sync documentation

- [Micronaut MongoDB Synchronous Driver documentation](https://micronaut-projects.github.io/micronaut-mongodb/latest/guide/index.html)

- [https://docs.mongodb.com](https://docs.mongodb.com)


## Feature ksp documentation

- [Micronaut Kotlin Symbol Processing (KSP) documentation](https://docs.micronaut.io/latest/guide/#kotlin)

- [https://kotlinlang.org/docs/ksp-overview.html](https://kotlinlang.org/docs/ksp-overview.html)


## Feature serialization-jackson documentation

- [Micronaut Serialization Jackson Core documentation](https://micronaut-projects.github.io/micronaut-serialization/latest/guide/)


## Feature micronaut-aot documentation

- [Micronaut AOT documentation](https://micronaut-projects.github.io/micronaut-aot/latest/guide/)


## Feature test-resources documentation

- [Micronaut Test Resources documentation](https://micronaut-projects.github.io/micronaut-test-resources/latest/guide/)


