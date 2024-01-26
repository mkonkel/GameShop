package features.games.add

import features.utils.Model
import widget.button.Button
import widget.input.InputText
import widget.topbar.TopBarModel

data class AddGameModel(
    val topBar: TopBarModel,
    val content: Content,
    val addButton: Button,
) : Model {
    data class Content(
        val name: InputText,
        val description: InputText,
        val price: InputText,
        val image: InputText,
    )
}
