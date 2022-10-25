import io.github.vantoozz.kli.commands.SimpleHello
import io.github.vantoozz.kli.kli
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class BaseCommandNameTest {

    @Test
    fun `it sets base command name`() {

        val command = kli(
            {},
            "some_name",
            SimpleHello()
        )

        assertEquals("some_name", command.commandName)
    }

    @Test
    fun `it does not set empty base command name`() {

        val command = kli(
            {},
            "",
            SimpleHello()
        )

        assertEquals("base-kli", command.commandName)
    }

    @Test
    fun `it does not set null base command name`() {

        val command = kli(
            {},
            null,
            SimpleHello()
        )

        assertEquals("base-kli", command.commandName)
    }

    @Test
    fun `it has default base command name`() {

        val command = kli(
            {},
            SimpleHello()
        )

        assertEquals("base-kli", command.commandName)
    }
}
