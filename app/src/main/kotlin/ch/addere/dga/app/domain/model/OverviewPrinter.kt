package ch.addere.dga.app.domain.model

import ch.addere.dga.graph.application.DependencyService
import ch.addere.dga.graph.application.ModuleService
import ch.addere.dga.importer.domain.model.Project

class OverviewPrinter(
    private val dependencyService: DependencyService,
    private val moduleService: ModuleService,
    private val printer: ConsolePrinter,
    private val project: Project
) {

    fun printToConsole() {
        val nofModules = moduleService.nofModules()
        val nofDependencies = dependencyService.nofDependencies()
        val nofUniqueDependencies = dependencyService.nofUniqueDependencies()

        printer.println("Analyse project \"${project.projectName()}\"")
        printer.println(String.format("%6d modules", nofModules))
        printer.println(
            String.format(
                "%6d dependencies (%d unique)",
                nofDependencies,
                nofUniqueDependencies
            )
        )
    }
}