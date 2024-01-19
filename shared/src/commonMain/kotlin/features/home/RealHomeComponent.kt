package features.home

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import features.BaseComponent
import features.factory.ComponentFactory
import features.utils.ModelState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.Serializable
import kotlin.coroutines.CoroutineContext

internal class RealHomeComponent(
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext,
    private val componentFactory: ComponentFactory,
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
            key = "HomeComponent",
            initialStack = { listOf(Config.Games) },
            childFactory = ::childFactory,
        )

    override val childStack: Value<ChildStack<*, HomeComponent.Child>> = stack

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

    @Serializable
    private sealed interface Config {
        @Serializable
        data object Games : Config

        @Serializable
        data object Users : Config
    }
}
