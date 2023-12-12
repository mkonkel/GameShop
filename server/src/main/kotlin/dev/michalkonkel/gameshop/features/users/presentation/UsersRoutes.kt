package dev.michalkonkel.gameshop.features.users.presentation

import dev.michalkonkel.gameshop.domain.user.UserRequest
import dev.michalkonkel.gameshop.features.users.domain.DatabaseUsersRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.auth.authenticate
import io.ktor.server.plugins.requestvalidation.RequestValidation
import io.ktor.server.plugins.requestvalidation.ValidationResult
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

fun Route.usersRouting() {
    val repo by inject<DatabaseUsersRepository>()

    authenticate("jwt-auth") {
        route("/users") {
            install(RequestValidation) {
                validate<UserRequest> { request ->
                    when {
                        request.password.isBlank() -> ValidationResult.Invalid("Password is required!")
                        request.username.isBlank() -> ValidationResult.Invalid("Username is required!")
                        request.password.length < 5 -> ValidationResult.Invalid("Password is too short!")
                        request.name.length < 5 -> ValidationResult.Invalid("Name is too short!")
                        request.username.length < 5 -> ValidationResult.Invalid("Username is too short!")
                        repo.existByName(request.username) -> ValidationResult.Invalid("User already exists!")
                        else -> ValidationResult.Valid
                    }
                }
            }
            post {
                val request = call.receive<UserRequest>()
                val user = repo.addUser(request)

                requireNotNull(user)

                call.respond(
                    status = HttpStatusCode.Created,
                    message = user
                )
            }

            get {
                val users = repo.getUsers()

                call.respond(
                    status = HttpStatusCode.OK,
                    message = users
                )
            }
        }
    }
}