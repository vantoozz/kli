import com.github.ajalt.clikt.core.context
import com.github.ajalt.clikt.testing.test
import com.github.ajalt.mordant.terminal.Terminal
import io.github.vantoozz.kli.commands.SimpleHello
import io.github.vantoozz.kli.kli
import io.github.vantoozz.kli.mocks.CliktTerminalMock
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

internal class SimpleCommandTest {

    @Test
    fun `it prints to console`() {
        val mock = CliktTerminalMock()

        kli(SimpleHello().apply {
            context {
                terminal = Terminal(mock)
            }
        }).test(listOf("simple-hello"))

        assertTrue { mock.printed("Hello") }
    }
}
