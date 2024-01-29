package dev.michalkonkel.gameshop.domain.orders

import dev.michalkonkel.gameshop.domain.games.Game
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class Order(
    val id: String,
    @SerialName("order_date")
    val orderDate: String,
    val games: List<Game>,
    val price: Float,
    val address: String,
)

@Serializable
data class AddOrderRequest(
    @SerialName("games")
    val gameIds: List<String>,
    val address: String,
)
