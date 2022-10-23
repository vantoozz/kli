package io.github.vantoozz.kli.runner.service

internal interface Service {
    fun call()
    fun callsMade(): Int
}
