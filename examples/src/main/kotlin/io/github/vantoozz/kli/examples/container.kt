package io.github.vantoozz.kli.examples

import io.github.vantoozz.dikt.MutableContainer
import io.github.vantoozz.dikt.put
import io.github.vantoozz.dikt.using
import io.github.vantoozz.kli.examples.service.DummyService
import io.github.vantoozz.kli.examples.service.Service

val container: MutableContainer.() -> Unit = {
    using(Config::class) { config ->
        put<Service> {
            DummyService(
                config.service.hostname,
                config.service.port
            )
        }
    }
}
