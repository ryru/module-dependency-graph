package ch.addere.mdg.graph.domain.service

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.containsExactlyInAnyOrder
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import ch.addere.mdg.graph.domain.model.Module
import org.junit.jupiter.api.Test

private val M1 = Module("m1")
private val M2 = Module("m2")
private val M3 = Module("m3")

class ModuleRepositoryTest {

    @Test
    fun `test empty repo has 0 module`() {
        val moduleRepository = ModuleRepository()

        assertThat(moduleRepository.getAllModules()).isEmpty()
    }

    @Test
    fun `test add and get module`() {
        val moduleRepository = ModuleRepository()

        moduleRepository.addModule(M1)

        assertThat(moduleRepository.getAllModules()).contains(M1)
        assertThat(moduleRepository.getModuleByName("m1")).transform { it.get() }.isEqualTo(M1)
    }


    @Test
    fun `test add several and get all modules`() {
        val moduleRepository = ModuleRepository()

        moduleRepository.addModule(setOf(M1, M2, M3))

        assertThat(moduleRepository.getAllModules()).containsExactlyInAnyOrder(M1, M2, M3)
        assertThat(moduleRepository.getModuleByName("m1")).transform { it.get() }.isEqualTo(M1)
        assertThat(moduleRepository.getModuleByName("m2")).transform { it.get() }.isEqualTo(M2)
        assertThat(moduleRepository.getModuleByName("m3")).transform { it.get() }.isEqualTo(M3)
    }
}
