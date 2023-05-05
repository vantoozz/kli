import io.github.vantoozz.dikt.put
import io.github.vantoozz.kli.commands.UseAutoClosable
import io.github.vantoozz.kli.kli
import io.github.vantoozz.kli.mocks.AutoClosableService
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class AutoClosableTest {

    @Test
    fun `it closes dependencies`() {
        val history = mutableListOf<String>()

        kli(
            { _ ->
                put { AutoClosableService(history) }
            },
            UseAutoClosable(),
        ).parse(listOf("use-auto-closable"))

        assertEquals(1, history.size)
    }
}
