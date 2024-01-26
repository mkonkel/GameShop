package features.games.list

import dev.michalkonkel.gameshop.domain.games.Game
import widget.button.Button

object GamesModelMapper {
    fun mapModel(
        games: List<Game>,
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
                        rating = 0,
                    )
                },
            addButton =
                Button(
                    style = Button.Style.FLOATING,
                    text = "Add",
                    onClick = { onAddClicked() },
                ),
        )
    }
}
