package widget.input

data class AuthInput(
    val type: Type,
) {
    enum class Type {
        LOGIN,
        PASSWORD,
    }
}
