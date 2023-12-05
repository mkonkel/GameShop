package dev.michalkonkel.gameshop

import dev.michalkonkel.gameshop.plugins.configureDI
import dev.michalkonkel.gameshop.plugins.configureRouting
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureDI()
}
