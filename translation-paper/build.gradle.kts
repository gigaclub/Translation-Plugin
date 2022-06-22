import org.apache.tools.ant.filters.ReplaceTokens

plugins {
    java
    id("com.github.johnrengelman.shadow")
}

val spigotPluginsDir: String? by project
val GITHUB_PACKAGES_USERID: String by project
val GITHUB_PACKAGES_IMPORT_TOKEN: String by project

repositories {
    maven {
        name = "papermc-repo"
        url = uri("https://papermc.io/repo/repository/maven-public/")
    }
    maven {
        name = "sonatype"
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }
    flatDir {
        dirs("/home/kevin/Development/JavaAPI/TranslationAPI/build/libs")
    }
    flatDir {
        dirs("/home/kevin/Development/JavaAPI/BaseAPI/build/libs")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")
    implementation("net.gigaclub:translationapi:14.0.1.0.5")
    implementation("net.gigaclub:baseapi:14.0.1.0.3")
    implementation("org.apache.xmlrpc:xmlrpc-client:3.1.3")
    implementation("org.json:json:20180813")
}

tasks {
    // If you open resources/plugins.yml you will see "@version@" as the version this code replaces this
    processResources {
        from(sourceSets["main"].resources) {
            val tokens = mapOf("version" to version)
            filter(ReplaceTokens::class, mapOf("tokens" to tokens))
            duplicatesStrategy = DuplicatesStrategy.INCLUDE
        }
    }

    build {
        dependsOn(shadowJar)
    }
}
