package widget.topbar

data class TopBarModel(
    val title: String = "",
    val onBackClick: () -> Unit = {},
)
