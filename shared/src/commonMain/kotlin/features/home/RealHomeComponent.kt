package features.home

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import dev.michalkonkel.gameshop.domain.roles.Role
import dev.michalkonkel.gameshop.domain.user.User
import features.BaseComponent
import features.factory.ComponentFactory
import features.utils.ModelState
import kotlinx.serialization.Serializable
import widget.bottombar.BottomBarModel
import widget.button.Button
import widget.topbar.TopBarModel
import kotlin.coroutines.CoroutineContext

internal class RealHomeComponent(
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext,
    private val componentFactory: ComponentFactory,
    private val onClose: () -> Unit,
    private val onGamesClick: (String) -> Unit,
    private val onAddGameClick: () -> Unit,
    private val user: User,
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
                    onClose()
                },
            ),
            bottomBar =
            BottomBarModel(
                items =
                buildList {
                    add(
                        Button(
                            text = "Games",
                            style = Button.Style.TEXT,
                            onClick = { games() },
                        )
                    )
                    add(
                        Button(
                            text = "Orders",
                            style = Button.Style.TEXT,
                            onClick = { orders() },
                        )
                    )

                    if (user.role == Role.ADMIN) {
                        add(
                            Button(
                                text = "Users",
                                style = Button.Style.TEXT,
                                onClick = { users() },
                            )
                        )
                    }
                }
            )
        )

    init {
        games()
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
            HomeComponent.Child.OrdersChild(
                componentFactory.createOrdersComponent(
                    componentContext
                )
            )

        Config.Users ->
            HomeComponent.Child.UsersChild(
                componentFactory.createUsersListComponent(
                    componentContext,
                ),
            )
    }

    private fun games() {
        model.topBar.title.value = "Games"
        navigation.bringToFront(Config.Games)
    }

    private fun orders() {
        model.topBar.title.value = "Orders"
        navigation.bringToFront(Config.Orders)
    }

    private fun users() {
        model.topBar.title.value = "Users"
        navigation.bringToFront(Config.Users)
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
