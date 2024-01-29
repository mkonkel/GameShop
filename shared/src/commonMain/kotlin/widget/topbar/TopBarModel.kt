package widget.topbar

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class TopBarModel(
    val navigationIcon: Icon = Icon.CLOSE,
    val title: MutableState<String> = mutableStateOf(""),
    val navigationAction: () -> Unit = {},
    val editAction: (() -> Unit)? = null,
) {
    constructor(
        icon: Icon = Icon.CLOSE,
        title: String = "",
        onBackClick: () -> Unit = {},
        editAction: (() -> Unit)? = null,
    ) : this(icon, mutableStateOf(title), onBackClick, editAction)

    enum class Icon {
        CLOSE,
        BACK,
    }
}
