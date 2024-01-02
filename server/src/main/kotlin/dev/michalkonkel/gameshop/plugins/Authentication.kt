package dev.michalkonkel.gameshop.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import dev.michalkonkel.gameshop.features.users.domain.DatabaseUsersRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.response.respond
import org.koin.ktor.ext.inject

const val SECRET = "SECRETT"
const val AUDIENCE = "http://0.0.0.0:3000"
const val ISSUER = "http://0.0.0.0:3000/login"

fun Application.configureAuthentication() {
    val userRepository by inject<DatabaseUsersRepository>()

    install(Authentication) {
        jwt("jwt-auth") {
            realm = "GameShopAccess"

            verifier(
                JWT
                    .require(Algorithm.HMAC256(SECRET))
                    .withAudience(AUDIENCE)
                    .withIssuer(ISSUER)
                    .build(),
            )

            validate { jwtCredential ->
                jwtCredential
                    .payload
                    .claims["userId"]
                    ?.asString()
                    ?.let {
                        if (userRepository.existById(it)) {
                            JWTPrincipal(jwtCredential.payload)
                        } else {
                            null
                        }
                    }
            }

            challenge { _, _ ->
                call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired!")
            }
        }
    }
}
