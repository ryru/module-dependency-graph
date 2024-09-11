plugins {
    id("ch.addere.dga.kotlin-library-conventions")
    `java-gradle-plugin`
    id("com.gradle.plugin-publish") version "1.3.0"
}

description = "Gradle plugin that is injected into to be analysed Gradle projects"

group = "ch.addere.dga.connectorplugin"
version = property("connectorPluginVersion").toString()

dependencies {
    implementation(project(":connector-model"))
}

gradlePlugin {
    website = "https://github.com/ryru/dependency-graph-analyser"
    vcsUrl = "https://github.com/ryru/dependency-graph-analyser.git"
    plugins {
        create("gradleDgaPlugin") {
            id = "ch.addere.dga.connectorplugin"
            displayName = "Dependency Graph Analyser (DGA) Connector Plugin"
            description = """
                The Dependency Graph Analyser (DGA) Connector Plugin is designed for use with Gradle multi-project builds. It collects module dependency data from all Gradle projects within the build and exports this information in a format compatible with the DGA CLI tool for further analysis and visualisation.
                The plugin is typically applied using a Gradle init script, making it easy to integrate into existing builds without modifying individual project configurations.
            """.trimIndent()
            tags = listOf("dependency", "graph", "analyser", "build", "mermaid")
            implementationClass = "ch.addere.dga.connectorplugin.ConnectorPlugin"
        }
    }
}
