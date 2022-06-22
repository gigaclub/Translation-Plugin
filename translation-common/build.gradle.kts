plugins {
    `java-library`
}

val GITHUB_PACKAGES_USERID: String by project
val GITHUB_PACKAGES_IMPORT_TOKEN: String by project

repositories {
//    maven {
//        name = "GitHubPackages"
//        url = uri("https://maven.pkg.github.com/gigaclub/translationapi")
//        metadataSources {
//            mavenPom()
//            artifact()
//        }
//        credentials {
//            username = GITHUB_PACKAGES_USERID
//            password = GITHUB_PACKAGES_IMPORT_TOKEN
//        }
//    }
    flatDir {
        dirs("/home/kevin/Development/JavaAPI/TranslationAPI/build/libs")
    }
    flatDir {
        dirs("/home/kevin/Development/JavaAPI/BaseAPI/build/libs")
    }
}

dependencies {
    api("net.gigaclub:translationapi:14.0.1.0.5")
    api("net.gigaclub:baseapi:14.0.1.0.3")
    api("org.apache.xmlrpc:xmlrpc-client:3.1.3")
    api("org.json:json:20180813")
}