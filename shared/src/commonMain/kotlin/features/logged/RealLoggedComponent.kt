package features.logged

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.webhistory.WebHistoryController
import com.arkivanov.decompose.value.Value
import deeplink.DeepLink
import features.BaseComponent
import features.Destination
import features.logged.factory.LoggedComponentFactory
import features.utils.ModelState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.Serializable
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalDecomposeApi::class)
internal class RealLoggedComponent(
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext,
    deepLink: DeepLink = DeepLink.None,
    webHistoryController: WebHistoryController? = null,
    rootNavigationRouter: LoggedNavigationRouter,
    private val componentFactory: LoggedComponentFactory,
) : BaseComponent(componentContext, coroutineContext), LoggedComponent {
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
            key = "LoggedComponent",
            source = navigation,
            serializer = Config.serializer(),
            initialStack = {
                getInitialStack(
                    webHistoryPaths = webHistoryController?.historyPaths,
                    deepLink = deepLink,
                )
            },
            childFactory = ::childFactory,
        )

    override val childStack: Value<ChildStack<*, LoggedComponent.Child>> = stack

    init {
        webHistoryController?.attach(
            navigator = navigation,
            stack = stack,
            serializer = Config.serializer(),
            getPath = Companion::getPathForConfig,
            getConfiguration = Companion::getConfigForPath,
        )

        rootNavigationRouter.observeDestinations()
            .onEach { handleNavigation(it) }
            .launchIn(scope)
    }

    private fun handleNavigation(destination: Destination) {
        println("Root navigation: $destination")
        when (destination) {
            LoggedNavigationRouter.RootDestination.GAMES -> navigation.push(Config.Games)
            LoggedNavigationRouter.RootDestination.USERS -> navigation.push(Config.Users)
            else -> navigation.push(Config.Games)
        }
    }

    private fun childFactory(
        config: Config,
        componentContext: ComponentContext,
    ) = when (config) {
        Config.Games -> LoggedComponent.Child.GamesChild(componentFactory.createGamesComponent())
        Config.Users -> LoggedComponent.Child.UsersChild(componentFactory.createUsersComponent())
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
