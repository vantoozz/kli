import io.github.vantoozz.dikt.bind
import io.github.vantoozz.dikt.dikt
import io.github.vantoozz.kli.commands.LogSomething
import io.github.vantoozz.kli.kli
import io.github.vantoozz.kli.mocks.Logger
import io.github.vantoozz.kli.mocks.LoggerMock
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

internal class HandlingCommandTest {

    @Test
    fun `it prints to console`() {

        val mock = LoggerMock()

        kli(
            {
                dikt {
                    bind<Logger>(mock)
                }
            },
            LogSomething(),
        ).parse(listOf("log-something"))

        assertTrue {
            mock.logged("some string")
        }
    }
}
