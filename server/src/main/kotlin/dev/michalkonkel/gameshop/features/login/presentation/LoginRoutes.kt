package dev.michalkonkel.gameshop.features.login.presentation

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import dev.michalkonkel.gameshop.domain.login.LoginRequest
import dev.michalkonkel.gameshop.domain.login.LoginResponse
import dev.michalkonkel.gameshop.features.users.domain.DatabaseUsersRepository
import dev.michalkonkel.gameshop.plugins.AUDIENCE
import dev.michalkonkel.gameshop.plugins.ISSUER
import dev.michalkonkel.gameshop.plugins.SECRET
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlinx.datetime.Clock
import org.koin.ktor.ext.inject
import java.util.Date

fun Route.loginRouting() {
    val repo by inject<DatabaseUsersRepository>()

    route("/login") {
        post {
            val loginRequest = call.receive<LoginRequest>()

            repo
                .getUserByUsernameAndPassword(loginRequest.username, loginRequest.password)
                ?.let {
                    val token =
                        JWT
                            .create()
                            .withAudience(AUDIENCE)
                            .withIssuer(ISSUER)
                            .withClaim("userId", it.id)
                            .withClaim("role", listOf(it.role.name))
                            .withExpiresAt(Date(Clock.System.now().toEpochMilliseconds() + 60000))
                            .sign(Algorithm.HMAC256(SECRET))

                    call.respond(LoginResponse(token))
                } ?: call.respondText(
                status = HttpStatusCode.BadRequest,
                text = "Invalid login or password",
            )
        }
    }
}
