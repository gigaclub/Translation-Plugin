plugins {
    `java-library`
}

val GITHUB_PACKAGES_USERID: String by project
val GITHUB_PACKAGES_IMPORT_TOKEN: String by project

repositories {
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/gigaclub/translationapi")
        metadataSources {
            mavenPom()
            artifact()
        }
        credentials {
            username = GITHUB_PACKAGES_USERID
            password = GITHUB_PACKAGES_IMPORT_TOKEN
        }
    }
}

dependencies {
    api("net.gigaclub:translationapi:14.0.1.0.3")
}