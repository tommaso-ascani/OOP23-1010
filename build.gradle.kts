plugins {
    java
    application
    // Plugin for jar file
    id("com.github.johnrengelman.shadow") version "8.1.1"
    // Plugin for java automated quality assurance
    id("org.danilopianini.gradle-java-qa") version "1.28.0"
}

repositories {
    mavenCentral()
}

val javaFXModules = listOf("base", "controls", "fxml", "swing", "graphics", "media")
val supportedPlatforms = listOf("linux", "mac", "win")

dependencies {

    val junitVersion = "5.9.2"
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")

    implementation ("org.json:json:20231013")

    val javaFxVersion = "15"
    for (platform in supportedPlatforms) {
        for (module in javaFXModules) {
            implementation("org.openjfx:javafx-$module:$javaFxVersion:$platform")
        }
    }
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events(*org.gradle.api.tasks.testing.logging.TestLogEvent.values())
        showStandardStreams = true
    }
}

tasks.jar {
    val mainResources = sourceSets.getByName("main").resources
    from(mainResources) {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }
}

application {
    mainClass.set("tenten.App")
}