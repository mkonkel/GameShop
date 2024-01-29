package features.games.detail

import features.utils.Model
import widget.button.Button
import widget.topbar.TopBarModel

data class GameDetailsModel(
    val topBar: TopBarModel,
    val content: Content,
    val addToCardButton: Button,
    val editButton: Button? = null,
) : Model {
    data class Content(
        val image: String,
        val description: String,
        val price: String,
    )
}
