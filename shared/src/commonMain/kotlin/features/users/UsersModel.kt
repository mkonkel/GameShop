package features.users

import features.utils.Model

data class UsersModel(
    val users: List<Item> =
        listOf(
            Item(
                username = "michalkonkel",
                name = "Michał Konkel",
            ),
            Item(
                username = "johnsmith",
                name = "John Smith",
            ),
            Item(
                username = "janek",
                name = "Jan Kowalski",
            ),
            Item(
                username = "jankowalski",
                name = "Jan Kowalski",
            ),
        ),
) : Model {
    data class Item(
        val username: String,
        val name: String,
    )
}
