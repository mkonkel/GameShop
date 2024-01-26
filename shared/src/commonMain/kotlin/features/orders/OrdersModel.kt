package features.games.detail

import features.utils.Model

data class OrdersModel(
    val items: List<Item> =
        listOf(
            Item(
                id = "1",
                orderDate = "2024-01-24",
                games =
                    listOf(
                        "Doom",
                        "Quake",
                    ),
                price = "9.99",
                address = "Gdańsk, ul. Gdańska 1",
            ),
            Item(
                id = "2",
                orderDate = "2024-01-24",
                games =
                    listOf(
                        "Duke Nukem",
                    ),
                price = "4.99",
                address = "Gdynia, ul. Gdyńska 1",
            ),
            Item(
                id = "3",
                orderDate = "2024-01-24",
                games =
                    listOf(
                        "Heretic",
                        "Hexen",
                    ),
                price = "19.99",
                address = "Gdańsk, ul. Gdańska 1",
            ),
            Item(
                id = "4",
                orderDate = "2024-01-24",
                games =
                    listOf(
                        "Wolfenstein",
                    ),
                price = "5.99",
                address = "Olsztyn, ul. Olsztyńska 1",
            ),
        ),
) : Model {
    data class Item(
        val id: String,
        val orderDate: String,
        val games: List<String>,
        val price: String,
        val address: String,
    )
}
