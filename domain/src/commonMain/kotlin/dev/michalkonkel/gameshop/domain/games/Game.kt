package dev.michalkonkel.gameshop.domain.games

import kotlinx.serialization.Serializable

@Serializable
data class Game(
    val id: String,
    val name: String,
    val price: String,
    val description: String,
    val imageUrl: String,
)

@Serializable
data class AddGameRequest(
    val name: String,
    val price: String,
    val description: String,
    val imageUrl: String,
)
