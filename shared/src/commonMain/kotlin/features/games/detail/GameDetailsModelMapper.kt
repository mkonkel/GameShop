package features.games.detail

import dev.michalkonkel.gameshop.domain.games.Game
import widget.button.Button
import widget.topbar.TopBarModel

object GameDetailsModelMapper {
    fun mapModel(
        game: Game,
        onBackClick: () -> Unit,
        onEditClick: () -> Unit,
    ) = GameDetailsModel(
        topBar =
            TopBarModel(
                icon = TopBarModel.Icon.BACK,
                title = game.name,
                onBackClick = { onBackClick() },
                editAction = {
                    onEditClick()
                },
            ),
        content =
            GameDetailsModel.Content(
                description = game.description,
                image = game.imageUrl,
                price = game.price,
            ),
        editButton =
            Button(
                style = Button.Style.FLOATING,
                text = "Edit",
                onClick = { println("Edit Game") },
            ),
        addToCardButton =
            Button(
                style = Button.Style.FILLED,
                text = "Add to cart",
                onClick = { println("Add to cart") },
            ),
    )
}
