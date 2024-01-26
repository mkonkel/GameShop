package widget.button

data class Button(
    val style: Style,
    val text: String,
    val onClick: () -> Unit,
) {
    enum class Style {
        FILLED,
        OUTLINED,
        TEXT,
        FLOATING,
    }
}
