package dev.michalkonkel.gameshop.plugins

import dev.michalkonkel.gameshop.features.games.presentation.gamesRouting
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.routing.Routing
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    install(Routing)

    routing {
        gamesRouting()
    }
}