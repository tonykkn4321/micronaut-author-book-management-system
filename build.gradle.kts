plugins {
    id("io.micronaut.application") version "4.5.4"
    id("com.gradleup.shadow") version "8.3.7"
    id("io.micronaut.test-resources") version "4.5.4"
    id("io.micronaut.aot") version "4.5.4"
}

version = "0.1"
group = "my.app"

repositories {
    mavenCentral()
}

dependencies {
    // Annotation processors
    annotationProcessor("io.micronaut.data:micronaut-data-processor")
    annotationProcessor("io.micronaut:micronaut-http-validation")
    annotationProcessor("io.micronaut.serde:micronaut-serde-processor")

    // Micronaut core
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari")
    implementation("io.micronaut.data:micronaut-data-jdbc")

    // Jakarta Persistence API
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")

    // HTTP client
    implementation("io.micronaut:micronaut-http-client")

    // Logging
    runtimeOnly("ch.qos.logback:logback-classic")

    // Database drivers (choose one or both)
    runtimeOnly("com.mysql:mysql-connector-j")              
    runtimeOnly("org.postgresql:postgresql:42.7.1")         

    // YAML support
    runtimeOnly("org.yaml:snakeyaml:2.2")

    // Testing
    testImplementation("io.micronaut.test:micronaut-test-junit5")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

application {
    mainClass.set("my.app.Application")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

graalvmNative {
    toolchainDetection.set(false)
}

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental.set(true)
        annotations.add("my.app.*")
    }
    aot {
        optimizeServiceLoading.set(false)
        convertYamlToJava.set(false)
        precomputeOperations.set(true)
        cacheEnvironment.set(true)
        optimizeClassLoading.set(true)
        deduceEnvironment.set(true)
        optimizeNetty.set(true)
        replaceLogbackXml.set(true)
    }
}

tasks.named<io.micronaut.gradle.docker.NativeImageDockerfile>("dockerfileNative") {
    jdkVersion.set("21")
}
