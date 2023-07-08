package ch.addere.mdg.domain.model

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.containsExactlyInAnyOrder
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.messageContains
import ch.addere.mdg.domain.model.GradleDsl.GROOVY
import ch.addere.mdg.domain.model.GradleDsl.KOTLIN
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.io.File

private val APP = Module("app")
private val LIST = Module("list")
private val UTILITIES = Module("utilities")

class SettingsFileTest {

    @ParameterizedTest
    @ValueSource(
        strings = [
            "multilinemultiproject.settings.gradle",
            "multilinemultiproject2.settings.gradle",
            "singlelinemultiproject.settings.gradle",
            "namedbuildfiles.settings.gradle",
            "singlelinenamedbuildfiles.settings.gradle"
        ]
    )
    fun `test groovy settings files`(file: String) {
        val settings = SettingsFile(getFile("/settings-files/groovy/$file"))

        val modules = settings.modules

        assertThat(settings.gradleDsl()).isEqualTo(GROOVY)
        assertThat(modules).containsExactlyInAnyOrder(APP, LIST, UTILITIES)
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "multilinemultiproject.settings.gradle.kts",
            "multilinemultiproject2.settings.gradle.kts",
            "singlelinemultiproject.settings.gradle.kts",
            "namedbuildfiles.settings.gradle.kts",
            "singlelinenamedbuildfiles.settings.gradle.kts"
        ]
    )
    fun `test kotlin settings files`(file: String) {
        val settings = SettingsFile(getFile("/settings-files/kotlin/$file"))

        val modules = settings.modules

        assertThat(settings.gradleDsl()).isEqualTo(KOTLIN)
        assertThat(modules).containsExactlyInAnyOrder(APP, LIST, UTILITIES)
    }

    @Test
    fun `test empty file`() {
        assertFailure { SettingsFile(getFile("/settings-files/empty.txt")) }
            .isInstanceOf(IllegalStateException::class)
            .messageContains("unable to find modules in")
    }

    @Test
    fun `test random file`() {
        assertFailure { SettingsFile(getFile("/settings-files/randomfile.txt")) }
            .isInstanceOf(IllegalStateException::class)
            .messageContains("unable to find modules in")
    }

    private fun getFile(path: String): File {
        return File(javaClass.getResource(path)!!.file)
    }
}
