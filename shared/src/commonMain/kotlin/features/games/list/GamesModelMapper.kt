package features.games.list

import dev.michalkonkel.gameshop.domain.games.Game
import dev.michalkonkel.gameshop.domain.roles.Role
import dev.michalkonkel.gameshop.domain.user.User
import widget.button.Button

object GamesModelMapper {
    fun mapModel(
        games: List<Game>,
        user: User,
        onAddClicked: () -> Unit,
    ): GamesListModel {
        return GamesListModel(
            games =
            games.map { game ->
                GamesListModel.Item(
                    id = game.id,
                    name = game.name,
                    description = game.description,
                    image = game.imageUrl,
                    price = game.price,
                )
            },
            addButton = if (user.role == Role.ADMIN) {
                Button(
                    style = Button.Style.FLOATING,
                    text = "Add",
                    onClick = { onAddClicked() },
                )
            } else {
                null
            }
        )
    }
}
