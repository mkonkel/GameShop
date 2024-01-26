package features.home

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import features.BaseComponent
import features.factory.ComponentFactory
import features.utils.ModelState
import kotlinx.serialization.Serializable
import widget.bottombar.BottomBarModel
import widget.topbar.TopBarModel
import kotlin.coroutines.CoroutineContext

internal class RealHomeComponent(
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext,
    private val componentFactory: ComponentFactory,
    private val onGamesClick: (String) -> Unit,
    private val onAddGameClick: () -> Unit,
) : BaseComponent(componentContext, coroutineContext), HomeComponent {
    private val navigation = StackNavigation<Config>()
    private val modelState: MutableValue<ModelState<HomeModel>> =
        MutableValue(ModelState.Loading())

    override val modelValue: Value<ModelState<HomeModel>> = modelState
    private val model: HomeModel =
        HomeModel(
            topBar =
                TopBarModel(
                    title = "Home",
                    onBackClick = {
                        navigation.pop()
                    },
                ),
            bottomBar =
                BottomBarModel(
                    items =
                        listOf(
                            BottomBarModel.Item(
                                label = "Games",
                                onClick = ::onGamesClick,
                            ),
                            BottomBarModel.Item(
                                label = "Orders",
                                onClick = ::onOrdersClick,
                            ),
                            BottomBarModel.Item(
                                label = "Users",
                                onClick = ::onUsersClick,
                            ),
                        ),
                ),
        )

    init {
        modelState.update { ModelState.Success(model) }
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

    private fun onUsersClick(item: BottomBarModel.Item) {
        model.bottomBar.selectItem(item)
        navigation.bringToFront(Config.Users)
    }

    private fun onGamesClick(item: BottomBarModel.Item) {
        model.bottomBar.selectItem(item)
        navigation.bringToFront(Config.Games)
    }

    private fun onOrdersClick(item: BottomBarModel.Item) {
        model.bottomBar.selectItem(item)
        navigation.bringToFront(Config.Orders)
    }

    private fun childFactory(
        config: Config,
        componentContext: ComponentContext,
    ) = when (config) {
        Config.Games ->
            HomeComponent.Child.GamesChild(
                componentFactory.createGamesListComponent(
                    componentContext = componentContext,
                    onDetails = { onGamesClick(it) },
                    onAdd = { onAddGameClick() },
                ),
            )

        Config.Orders ->
            HomeComponent.Child.OrdersChild(componentFactory.createOrdersComponent(componentContext))

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
        data object Orders : Config

        @Serializable
        data object Users : Config
    }
}
