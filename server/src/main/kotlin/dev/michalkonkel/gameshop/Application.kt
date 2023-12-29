package dev.michalkonkel.gameshop

import dev.michalkonkel.gameshop.plugins.configureAuthentication
import dev.michalkonkel.gameshop.plugins.configureCORS
import dev.michalkonkel.gameshop.plugins.configureDI
import dev.michalkonkel.gameshop.plugins.configureDatabase
import dev.michalkonkel.gameshop.plugins.configureRouting
import dev.michalkonkel.gameshop.plugins.configureSerialization
import dev.michalkonkel.gameshop.plugins.configureStatusPages
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.resources.Resources

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0") {
        module()
    }.start(wait = true)
}

fun Application.module() {
    install(Resources)
    configureAuthentication()
    configureSerialization()
    configureRouting()
    configureDI()
    configureDatabase()
    configureStatusPages()
    configureCORS()
}
