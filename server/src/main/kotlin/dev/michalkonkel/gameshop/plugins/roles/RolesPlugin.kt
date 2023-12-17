package dev.michalkonkel.gameshop.plugins.roles

import dev.michalkonkel.gameshop.domain.roles.Role
import io.ktor.server.application.createRouteScopedPlugin
import io.ktor.server.application.install
import io.ktor.server.auth.AuthenticationChecked
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.request.path
import io.ktor.server.routing.Route
import io.ktor.server.routing.RouteSelector
import io.ktor.server.routing.RouteSelectorEvaluation
import io.ktor.server.routing.RoutingResolveContext

fun Route.withRole(
    vararg roles: Role,
    build: Route.() -> Unit,
) {
    val route =
        createChild(
            object : RouteSelector() {
                override fun evaluate(
                    context: RoutingResolveContext,
                    segmentIndex: Int,
                ): RouteSelectorEvaluation {
                    // Transparent selector means that it does not consume anything from the URL, so child routes can match.
                    return RouteSelectorEvaluation.Transparent
                }
            },
        )

    route.install(RoleAuthorizationPlugin) {
        roles(roles.toSet())
    }

    route.build()
}

internal class AuthorizationConfiguration {
    val requiredRoles: MutableSet<Role> = mutableSetOf()

    fun roles(roles: Set<Role>) {
        requiredRoles.addAll(roles)
    }
}

internal val RoleAuthorizationPlugin =
    createRouteScopedPlugin("RoleAuthorizationPlugin", ::AuthorizationConfiguration) {
        on(AuthenticationChecked) { call ->
            val principal = call.principal<JWTPrincipal>() ?: return@on
            val roles =
                principal
                    .payload
                    .getClaim("role")
                    .asList(String::class.java)
                    .map { Role.valueOf(it) }

            if (pluginConfig.requiredRoles.isNotEmpty() && roles.intersect(pluginConfig.requiredRoles).isEmpty()) {
                throw AuthorizationException(
                    route = call.request.path(),
                    reasons = listOf("You don`t have required role"),
                )
            }
        }
    }

class AuthorizationException(
    route: String,
    val reasons: List<String>,
) : IllegalArgumentException("You don`t have access to $route. Reasons: ${reasons.joinToString()}")
