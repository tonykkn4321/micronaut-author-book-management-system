# Project structure:
author-book-api/
├── build.gradle
├── settings.gradle
src/main/java/
└── com/example/app/
    ├── models/
    │   ├── Author.java
    │   └── Book.java
    │── repositories/
    │   ├── AuthorRepository.java
    │   └── BookRepository.java
    ├── routes/
    │   ├── AuthorController.java
    │   └── BookController.java
    └── Application.java
src/main/resources/
├── application.yml
├── application-dev.yml
├── application-test.yml
└── application-prod.yml

└── gradle/
    └── wrapper/



# Start a prject:
mn create-app example.micronaut --features jdbc,mysql,postgresql

# Run the Application:
./gradlew run
gradlew run    (for using cmd)

# If encounter build failure, run to have more detailed errors:
gradlew build --info

# If modified build.gradle, run:
./gradlew clean build
