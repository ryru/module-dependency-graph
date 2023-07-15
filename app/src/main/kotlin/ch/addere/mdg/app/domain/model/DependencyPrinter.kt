package ch.addere.mdg.app.domain.model

import ch.addere.mdg.graph.application.DependencyService

class DependencyPrinter(
    private val dependencyService: DependencyService,
    private val printer: ConsolePrinter,
) {

    fun printToConsole() {
        val dependencies = dependencyService.configuraitonsWithOccurence()
        if (dependencies.isNotEmpty()) {
            printer.println()
            val ordered = dependencies.toList().sortedBy { (_, value) -> value }.toMap()
            ordered.forEach { (configuration, amount) -> printer.println("${configuration.name}, $amount") }
        }
    }
}
