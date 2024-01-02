package dev.michalkonkel.gameshop.features.games.presentation

import dev.michalkonkel.gameshop.domain.games.GameRequest
import dev.michalkonkel.gameshop.domain.roles.Role
import dev.michalkonkel.gameshop.plugins.roles.withRole
import dev.michalkonkel.gameshop.repository.games.GamesRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.resources.get
import io.ktor.server.resources.post
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import org.koin.ktor.ext.inject

fun Route.gamesRouting() {
    val repo by inject<GamesRepository>()

    authenticate("jwt-auth") {
        withRole(Role.USER, Role.ADMIN) {
            get<GamesResources> {
                val games = repo.getGames()

                call.respond(
                    status = HttpStatusCode.OK,
                    message = games,
                )
            }
        }
        withRole(Role.USER) {
            get<GamesResources.Id> { request ->
                val game = repo.getGame(request.id)

                game?.let { call.respond(it) } ?: call.respondText(
                    status = HttpStatusCode.BadRequest,
                    text = "No such game!",
                )
            }
        }
        withRole(Role.ADMIN) {
            post<GamesResources.New> {
                val gameRequest = call.receive<GameRequest>()
                val newGame = repo.addGame(gameRequest)

                call.respond(newGame)
            }
        }
    }
}
