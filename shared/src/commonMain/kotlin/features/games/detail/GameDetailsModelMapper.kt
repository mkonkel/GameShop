package features.games.detail

import dev.michalkonkel.gameshop.domain.games.Game
import widget.button.Button
import widget.topbar.TopBarModel

object GameDetailsModelMapper {
    fun mapModel(
        game: Game,
        onBackClick: () -> Unit,
    ) = GameDetailsModel(
        topBar =
            TopBarModel(
                title = game.name,
                onBackClick = { onBackClick() },
            ),
        content =
            GameDetailsModel.Content(
                description = game.description,
                image = game.imageUrl,
                price = game.price,
                addToCardButton =
                    Button(
                        style = Button.Style.PRIMARY,
                        text = "Add to cart",
                        onClick = { println("Add to cart") },
                    ),
            ),
        editButton =
            Button(
                style = Button.Style.FLOATING,
                text = "Edit",
                onClick = { println("Edit Game") },
            ),
    )
}
