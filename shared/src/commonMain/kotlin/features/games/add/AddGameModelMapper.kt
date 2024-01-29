package features.games.add

import androidx.compose.runtime.mutableStateOf
import dev.michalkonkel.gameshop.domain.games.Game
import widget.button.Button
import widget.input.InputText
import widget.topbar.TopBarModel

object AddGameModelMapper {
    fun mapModel(
        game: Game?,
        onBackClick: () -> Unit,
        onAddGameClick: () -> Unit,
    ) = AddGameModel(
        topBar =
            TopBarModel(
                navigationIcon = TopBarModel.Icon.BACK,
                title = mutableStateOf(screenName(game)),
                navigationAction = onBackClick,
            ),
        content =
            AddGameModel.Content(
                name =
                    InputText(
                        text = mutableStateOf(game?.name ?: ""),
                        label = "Name",
                        maxLines = 1,
                    ),
                description =
                    InputText(
                        text = mutableStateOf(game?.description ?: ""),
                        label = "Description",
                        maxLines = 10,
                    ),
                price =
                    InputText(
                        text = mutableStateOf(game?.price ?: ""),
                        label = "Price",
                        maxLines = 1,
                    ),
                image =
                    InputText(
                        text = mutableStateOf(game?.imageUrl ?: ""),
                        label = "Image URL",
                        maxLines = 1,
                    ),
            ),
        addButton =
            Button(
                style = Button.Style.FILLED,
                text = "Add Game",
                onClick = onAddGameClick,
            ),
    )

    private fun screenName(game: Game?) = if (game == null) "Add Game" else "Edit Game"
}
