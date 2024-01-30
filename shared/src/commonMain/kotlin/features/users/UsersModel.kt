package features.users

import features.utils.Model

data class UsersModel(
    val users: List<Item>,
) : Model {
    data class Item(
        val username: String,
        val name: String,
    )
}
