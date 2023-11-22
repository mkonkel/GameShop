package dev.michalkonkel.gameshop.features.games.presentation

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route

fun Route.gamesRouting() {
    route("/games") {
        get {
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