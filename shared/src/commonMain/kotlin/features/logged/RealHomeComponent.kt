package features.logged

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.webhistory.WebHistoryController
import com.arkivanov.decompose.value.Value
import deeplink.DeepLink
import features.BaseComponent
import features.logged.factory.LoggedComponentFactory
import features.utils.ModelState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.Serializable
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalDecomposeApi::class)
internal class RealHomeComponent(
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext,
    deepLink: DeepLink = DeepLink.None,
    webHistoryController: WebHistoryController? = null,
    private val componentFactory: LoggedComponentFactory,
) : BaseComponent(componentContext, coroutineContext), HomeComponent {
    private val navigation = StackNavigation<Config>()

    private val _modelState: MutableStateFlow<ModelState<HomeModel>> =
        MutableStateFlow(ModelState.Loading())

    override val modelState: StateFlow<ModelState<HomeModel>>
        get() = _modelState.asStateFlow()

    init {
        _modelState.value = ModelState.Success(HomeModel("Hello in GameShop!"))
    }

    private val stack =
        childStack(
            source = navigation,
            serializer = Config.serializer(),
            handleBackButton = true,
            key = "LoggedComponent",
            initialStack = {
                getInitialStack(
                    webHistoryPaths = webHistoryController?.historyPaths,
                    deepLink = deepLink,
                )
            },
            childFactory = ::childFactory,
        )

    override val childStack: Value<ChildStack<*, HomeComponent.Child>> = stack

    init {
        webHistoryController?.attach(
            navigator = navigation,
            stack = stack,
            serializer = Config.serializer(),
            getPath = Companion::getPathForConfig,
            getConfiguration = Companion::getConfigForPath,
        )
    }

    override fun onUsersClick() {
        navigation.bringToFront(Config.Users)
    }

    override fun onGamesClick() {
        navigation.bringToFront(Config.Games)
    }

    private fun childFactory(
        config: Config,
        componentContext: ComponentContext,
    ) = when (config) {
        Config.Games ->
            HomeComponent.Child.GamesChild(
                componentFactory.createGamesListComponent(
                    componentContext,
                ),
            )

        Config.Users ->
            HomeComponent.Child.UsersChild(
                componentFactory.createUsersListComponent(
                    componentContext,
                ),
            )
    }

    private companion object {
        private const val WEB_PATH_GAMES = "games"
        private const val WEB_PATH_USERS = "users"

        private fun getInitialStack(
            webHistoryPaths: List<String>?,
            deepLink: DeepLink,
        ): List<Config> =
            webHistoryPaths
                ?.takeUnless(List<*>::isEmpty)
                ?.map(::getConfigForPath)
                ?: getInitialStack(deepLink)

        private fun getInitialStack(deepLink: DeepLink): List<Config> =
            when (deepLink) {
                is DeepLink.None -> listOf(Config.Games)
                is DeepLink.Web -> listOf(getConfigForPath(deepLink.path))
            }

        private fun getPathForConfig(config: Config): String =
            when (config) {
                Config.Games -> "/$WEB_PATH_GAMES"
                Config.Users -> "/$WEB_PATH_USERS"
            }

        private fun getConfigForPath(path: String): Config =
            when (path.removePrefix("/")) {
                WEB_PATH_GAMES -> Config.Games
                WEB_PATH_USERS -> Config.Users
                else -> Config.Games
            }
    }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object Games : Config

        @Serializable
        data object Users : Config
    }
}
