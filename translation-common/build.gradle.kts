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
        credentials {
            username = System.getenv("GITHUB_PACKAGES_USERID") ?: "kevtvkevin"
            password = System.getenv("GITHUB_PACKAGES_IMPORT_TOKEN") ?: "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"
        }
    }
}

dependencies {
    api("org.apache.xmlrpc:xmlrpc-client:3.1.3")
    api("net.gigaclub:translationapi:14.0.1.0.0")
}