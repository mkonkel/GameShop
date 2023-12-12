package dev.michalkonkel.gameshop.features.games.presentation

import dev.michalkonkel.gameshop.repository.GamesRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

fun Route.gamesRouting() {
    val repo by inject<GamesRepository>()

    route("/games") {
        get {
            val games = repo.getGames()
            call.respondText(text = "There are no games in our shop yet...", status = HttpStatusCode.OK)
        }
        get("/{id}") {
            TODO()
        }
        post {
            TODO()
        }
        put("/{id}") {
            TODO()
        }
        delete("/{id}") {
            TODO()
        }
    }
}