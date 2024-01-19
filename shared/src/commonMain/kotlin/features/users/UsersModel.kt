package features.users

import features.utils.Model

data class UsersModel(
    val users: List<String> =
        listOf(
            "John Doe",
            "Jane Doe",
            "John Smith",
            "Jane Smith",
            "John Johnson",
            "Jane Johnson",
            "John Williams",
            "Jane Williams",
        ),
) : Model
