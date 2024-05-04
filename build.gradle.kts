plugins {
    `java-library`
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("xyz.jpenilla.run-paper") version "2.3.0"
}

group = "com.rokucraft"
version = "1.0.3-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://oss.sonatype.org/content/groups/public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.4-R0.1-SNAPSHOT")
    compileOnly("org.spongepowered:configurate-yaml:4.1.2")
    implementation("org.incendo:cloud-paper:2.0.0-beta.6")
    implementation("com.github.stefvanschie.inventoryframework:IF:0.10.13")
}

tasks {
    assemble {
        dependsOn(shadowJar)
    }
    shadowJar {
        archiveClassifier = ""
        isEnableRelocation = true
        relocationPrefix = "${project.group}.${project.name}.libs"
    }
    processResources {
        val props = "version" to version
        inputs.properties(props)
        filteringCharset = "UTF-8"
        filesMatching("plugin.yml") {
            expand(props)
        }
    }
    runServer {
        minecraftVersion("1.20.6")
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
