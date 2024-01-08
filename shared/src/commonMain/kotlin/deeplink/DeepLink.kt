package deeplink

sealed interface DeepLink {
    data object None : DeepLink
    class Web(val path: String) : DeepLink
}