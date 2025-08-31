# Project structure:
author-book-api/
├── build.gradle
├── settings.gradle
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/
│   │   │       ├── AuthorBookApiApplication.java
│   │   │       ├── controller/
│   │   │       │   ├── AuthorController.java
│   │   │       │   └── BookController.java
│   │   │       ├── entity/
│   │   │       │   ├── Author.java
│   │   │       │   └── Book.java
│   │   │       └── repository/
│   │   │           ├── AuthorRepository.java
│   │   │           └── BookRepository.java
│   │   └── resources/
│   │       └── application.yml
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
