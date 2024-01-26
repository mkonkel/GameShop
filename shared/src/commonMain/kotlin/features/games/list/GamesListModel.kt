package features.games.list

import features.utils.Model
import widget.button.Button

data class GamesListModel(
    val games: List<Item>,
    val addButton: Button? = null,
) : Model {
    data class Item(
        val id: String,
        val name: String,
        val description: String,
        val image: String,
        val rating: Int,
    )
}
