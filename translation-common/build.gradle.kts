plugins {
    `java-library`
}

repositories {
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/gigaclub/translationapi")
        metadataSources {
            mavenPom()
            artifact()
        }
    }
}

dependencies {
    api("net.gigaclub:translationapi:14.0.1.0.1")
}