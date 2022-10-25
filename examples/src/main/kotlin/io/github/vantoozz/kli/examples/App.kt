package io.github.vantoozz.kli.examples

import io.github.vantoozz.kli.examples.commands.CallService
import io.github.vantoozz.kli.examples.commands.DescribeService
import io.github.vantoozz.kli.runner.kli

fun main(args: Array<String>) =
    kli<Config>(container, args) {
//        +DescribeService()
        +CallService()
    }
