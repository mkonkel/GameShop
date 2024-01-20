package widget.input

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.update

data class InputText(
    val text: MutableValue<String> = MutableValue(""),
    val label: String = "",
    val maxLines: Int = 1,
    val type: Type = Type.NORMAL,
) {
    enum class Type {
        NORMAL,
        SECURE,
    }

    fun updateText(value: String) {
        text.update { value }
    }
}
