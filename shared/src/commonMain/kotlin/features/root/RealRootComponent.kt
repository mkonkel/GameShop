package features.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.webhistory.WebHistoryController
import com.arkivanov.decompose.value.Value
import deeplink.DeepLink
import features.ComponentFactory
import features.games.RealGamesComponent
import features.users.RealUsersComponent
import kotlinx.serialization.Serializable

@OptIn(ExperimentalDecomposeApi::class)
internal class RealRootComponent(
    componentContext: ComponentContext,
    deepLink: DeepLink = DeepLink.None,
    webHistoryController: WebHistoryController? = null,
    private val componentFactory: ComponentFactory,
) : RootComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<Config>()

    private val stack = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialStack = { getInitialStack(webHistoryPaths = webHistoryController?.historyPaths, deepLink = deepLink) },
        childFactory = ::childFactory
    )

    override val childStack: Value<ChildStack<*, RootComponent.Child>> = stack

    init {
        webHistoryController?.attach(
            navigator = navigation,
            stack = stack,
            serializer = Config.serializer(),
            getPath = Companion::getPathForConfig,
            getConfiguration = Companion::getConfigForPath,
        )
    }

    private fun childFactory(config: Config, componentContext: ComponentContext) =
        when (config) {
            Config.Login -> RootComponent.Child.LoginChild(componentFactory.createLoginComponent())
            Config.Games -> RootComponent.Child.GamesChild(RealGamesComponent(componentContext))
            Config.Users -> RootComponent.Child.UsersChild(RealUsersComponent(componentContext))
        }

    private companion object {
        private const val WEB_PATH_LOGIN = "login"
        private const val WEB_PATH_GAMES = "games"
        private const val WEB_PATH_USERS = "users"

        private fun getInitialStack(webHistoryPaths: List<String>?, deepLink: DeepLink): List<Config> =
            webHistoryPaths
                ?.takeUnless(List<*>::isEmpty)
                ?.map(::getConfigForPath)
                ?: getInitialStack(deepLink)

        private fun getInitialStack(deepLink: DeepLink): List<Config> =
            when (deepLink) {
                is DeepLink.None -> listOf(Config.Login)
                is DeepLink.Web -> listOf(getConfigForPath(deepLink.path))
            }

        private fun getPathForConfig(config: Config): String =
            when (config) {
                Config.Login -> "/$WEB_PATH_LOGIN"
                Config.Games -> "/$WEB_PATH_GAMES"
                Config.Users -> "/$WEB_PATH_USERS"
            }

        private fun getConfigForPath(path: String): Config =
            when (path.removePrefix("/")) {
                WEB_PATH_LOGIN -> Config.Login
                WEB_PATH_GAMES -> Config.Games
                WEB_PATH_USERS -> Config.Users
                else -> Config.Login
            }
    }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object Login : Config

        @Serializable
        data object Games : Config

        @Serializable
        data object Users : Config
    }
}