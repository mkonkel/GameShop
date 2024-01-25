package features.games.detail

import features.utils.Model

data class OrdersModel(
    val items: List<Item> =
        listOf(
            Item(
                id = "1",
                date = "Doom",
                price = "9.99",
                quantity = "1",
            ),
            Item(
                id = "2",
                date = "Doom 2",
                price = "9.99",
                quantity = "1",
            ),
            Item(
                id = "3",
                date = "Quake",
                price = "9.99",
                quantity = "1",
            ),
            Item(
                id = "4",
                date = "Quake 2",
                price = "19.99",
                quantity = "3",
            ),
        ),
) : Model {
    data class Item(
        val id: String,
        val date: String,
        val price: String,
        val quantity: String,
    )
}
