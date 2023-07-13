package ch.addere.mdg.app.domain.service

import ch.addere.mdg.app.domain.model.CommandArgument
import ch.addere.mdg.app.domain.model.ConsolePrinter
import ch.addere.mdg.app.domain.model.MermaidPrinter
import ch.addere.mdg.app.domain.model.ModulePrinter
import ch.addere.mdg.app.domain.model.OverviewPrinter

class DependencyCommand(
    private val argument: CommandArgument,
    private val mermaid: MermaidPrinter,
    private val modules: ModulePrinter,
    private val overview: OverviewPrinter,
    private val printer: ConsolePrinter,
) {

    fun run() {
        printer.println()
        overview.printToConsole()

        if (argument.isAllModules) {
            modules.printToConsole()
        } else if (argument.isMermaidGraph) {
            mermaid.printToConsole()
        }
        printer.println()
    }
}