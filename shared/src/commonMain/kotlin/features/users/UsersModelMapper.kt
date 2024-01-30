package features.users

import dev.michalkonkel.gameshop.domain.user.User

object UsersModelMapper {
    fun mapModel(users: List<User>): UsersModel {
        return UsersModel(
            users =
                users.map {
                    UsersModel.Item(
                        username = it.username,
                        name = it.name,
                    )
                },
        )
    }
}
