package widget.input

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class InputText(
    val text: MutableState<String> = mutableStateOf(""),
    val label: String = "",
    val maxLines: Int = 1,
    val type: Type = Type.NORMAL,
) {
    enum class Type {
        NORMAL,
        SECURE,
    }
}
