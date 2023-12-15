package dev.michalkonkel.gameshop.domain.roles

import kotlinx.serialization.Serializable

@Serializable
enum class Role {
    ADMIN,
    USER
}
