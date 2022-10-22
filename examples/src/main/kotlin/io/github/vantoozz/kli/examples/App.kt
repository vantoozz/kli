package io.github.vantoozz.kli.examples

import io.github.vantoozz.kli.config.kli
import io.github.vantoozz.kli.examples.commands.CallService
import io.github.vantoozz.kli.examples.commands.DescribeService

fun main(args: Array<String>) {
    kli<Config>(
        container,
        DescribeService(),
        CallService(),
    ).main(args)
}
