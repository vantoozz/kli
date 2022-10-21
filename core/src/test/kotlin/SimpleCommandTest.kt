import com.github.ajalt.clikt.core.context
import io.github.vantoozz.kli.mocks.CliktConsoleMock
import io.github.vantoozz.kli.commands.SimpleHello
import io.github.vantoozz.kli.kli
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

internal class SimpleCommandTest {

    @Test
    fun `it prints to console`() {
        val mock = CliktConsoleMock()

        kli(SimpleHello().apply {
            context {
                console = mock
            }
        }).parse(listOf("simple-hello"))

        assertTrue { mock.printed("Hello") }
    }
}
