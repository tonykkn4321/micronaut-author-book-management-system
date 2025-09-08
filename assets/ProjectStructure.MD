# Project structure:
author-book-api/
├── build.gradle
├── settings.gradle
src/main/java/
└── my/app/
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
mn create-app my-app --features mysql,data-jdbc

# files modified:
settings.gradle
build.gradle

# Upgrade Gradle Wrapper:
gradlew wrapper --gradle-version 8.4
# Clean Your Cache
# Delete your local Gradle cache to force fresh resolution:
rd /s /q "%USERPROFILE%\.gradle\caches"

# Set Environment:
export MICRONAUT_ENVIRONMENTS=dev  (for linux)
set MICRONAUT_ENVIRONMENTS=dev   (for cmd)


# Run the Application:
./gradlew run
gradlew run    (for using cmd)

# If encounter build failure, run to have more detailed errors:
gradlew build --info

# If modified build.gradle, run:
./gradlew clean build

# Bypass test during build:
./gradlew build -x test
