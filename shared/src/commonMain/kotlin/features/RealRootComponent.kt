package features

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.router.stack.webhistory.WebHistoryController
import com.arkivanov.decompose.value.Value
import deeplink.DeepLink
import features.factory.ComponentFactory
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.Serializable
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalDecomposeApi::class)
internal class RealRootComponent(
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext,
    navigationRouter: NavigationRouter,
    private val deepLink: DeepLink = DeepLink.None,
    private val webHistoryController: WebHistoryController? = null,
    private val componentFactory: ComponentFactory,
) : BaseComponent(componentContext, coroutineContext), RootComponent {
    private val navigation = StackNavigation<Config>()

    private val stack =
        childStack(
            key = "RootComponent",
            source = navigation,
            serializer = Config.serializer(),
            handleBackButton = true,
            initialStack = {
                getInitialStack(
                    webHistoryPaths = webHistoryController?.historyPaths,
                    deepLink = deepLink,
                )
            },
            childFactory = ::childFactory,
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

        navigationRouter.observeDestinations()
            .onEach { handleNavigation(it) }
            .launchIn(scope)
    }

    private fun handleNavigation(destination: NavigationRouter.Destination) {
        println("Root navigation: $destination")
        when (destination) {
            NavigationRouter.Destination.LOGIN -> navigation.pushNew(Config.Login)
            NavigationRouter.Destination.REGISTER -> navigation.pushNew(Config.Register)
            NavigationRouter.Destination.HOME -> navigation.pushNew(Config.Home)
            NavigationRouter.Destination.GAME_DETAILS -> navigation.pushNew(Config.GameDetails)
            else -> navigation.bringToFront(Config.Login)
        }
    }

    private fun childFactory(
        config: Config,
        componentContext: ComponentContext,
    ) = when (config) {
        Config.Login -> {
            RootComponent.Child.LoginChild(componentFactory.createLoginComponent(componentContext))
        }

        Config.Register -> {
            RootComponent.Child.RegisterChild(componentFactory.createRegisterComponent(componentContext))
        }

        Config.Home -> {
            RootComponent.Child.HomeChild(componentFactory.createHomeComponent(componentContext))
        }

        Config.GameDetails -> {
            RootComponent.Child.GameDetails(componentFactory.createGameDetailsComponent(componentContext))
        }
    }

    private companion object {
        private const val WEB_PATH_LOGIN = "login"
        private const val WEB_PATH_REGISTER = "register"
        private const val WEB_PATH_HOME = "home"
        private const val WEB_PATH_GAMES_DETAIL = "games/detail"

        private fun getInitialStack(
            webHistoryPaths: List<String>?,
            deepLink: DeepLink,
        ): List<Config> =
            webHistoryPaths
                ?.takeUnless(List<*>::isEmpty)
                ?.map(Companion::getConfigForPath)
                ?: getInitialStack(deepLink)

        private fun getInitialStack(deepLink: DeepLink): List<Config> =
            when (deepLink) {
                is DeepLink.None -> listOf(Config.Login)
                is DeepLink.Web -> listOf(getConfigForPath(deepLink.path))
            }

        private fun getPathForConfig(config: Config): String =
            when (config) {
                Config.Login -> "/$WEB_PATH_LOGIN"
                Config.Register -> "/$WEB_PATH_REGISTER"
                Config.Home -> "/$WEB_PATH_HOME"
                Config.GameDetails -> "/$WEB_PATH_GAMES_DETAIL"
            }

        private fun getConfigForPath(path: String): Config =
            when (path.removePrefix("/")) {
                WEB_PATH_LOGIN -> Config.Login
                WEB_PATH_REGISTER -> Config.Register
                WEB_PATH_HOME -> Config.Home
                WEB_PATH_GAMES_DETAIL -> Config.GameDetails
                else -> Config.Login
            }
    }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object Login : Config

        @Serializable
        data object Register : Config

        @Serializable
        data object Home : Config

        @Serializable
        data object GameDetails : Config
    }
}