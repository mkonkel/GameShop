package dev.michalkonkel.gameshop.domain.user

import dev.michalkonkel.gameshop.domain.roles.Role
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val name: String,
    val username: String,
    val role: Role,
)

@Serializable
data class UserRequest(
    val name: String,
    val username: String,
    val password: String
)