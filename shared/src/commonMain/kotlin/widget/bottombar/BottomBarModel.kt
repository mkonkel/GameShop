package widget.bottombar

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class BottomBarModel(
    val items: List<Item>,
) {
    data class Item(
        val label: String,
        val onClick: (Item) -> Unit = {},
        val isSelected: MutableState<Boolean> = mutableStateOf(false),
    )

    fun selectItem(item: Item) {
        items.forEach { it.isSelected.value = false }
        item.isSelected.value = true
    }
}
