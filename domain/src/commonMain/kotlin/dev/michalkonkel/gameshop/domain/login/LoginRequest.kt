package dev.michalkonkel.gameshop.domain.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String,
)

@Serializable
data class LoginResponse(
    @SerialName("token")
    val token: String,
)
