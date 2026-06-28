plugins {
    id("java-library")
    id("xyz.jpenilla.run-paper") version "3.0.2"
    id("com.gradleup.shadow") version "9.4.3"
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.aikar.co/content/groups/aikar/")
    maven("https://hub.spigotmc.org/nexus/content/groups/public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:26.2.build.+")
    implementation("co.aikar:acf-paper:0.5.0-SNAPSHOT")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(25)
}

tasks {
    runServer {
        minecraftVersion("26.1.2")
        jvmArgs("-Xms2G", "-Xmx2G")
    }

    processResources {
        val props = mapOf("version" to version)
        filesMatching("plugin.yml") {
            expand(props)
        }
    }

    shadowJar {
        archiveBaseName.set(project.name)
        archiveVersion.set(project.version.toString())
        archiveClassifier.set("")

        // probably never gonna conflict though :/
        relocate("co.aikar.commands", "${project.group}.libs.acf")
        relocate("co.aikar.locales", "${project.group}.libs.locales")
    }

    build {
        dependsOn(shadowJar)
    }
}