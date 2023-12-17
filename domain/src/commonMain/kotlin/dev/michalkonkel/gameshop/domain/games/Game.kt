package dev.michalkonkel.gameshop.domain.games

import kotlinx.serialization.Serializable

@Serializable
data class Game(
    val id: String,
    val name: String,
    val price: String,
    val description: String,
)

@Serializable
data class GameRequest(
    val name: String,
    val price: String,
    val description: String,
)
