package features.factory

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.webhistory.WebHistoryController
import deeplink.DeepLink
import di.DI
import features.RealRootComponent
import features.RootComponent
import features.games.add.AddGameComponent
import features.games.add.RealAddGameComponent
import features.games.detail.GameDetailsComponent
import features.games.detail.OrdersComponent
import features.games.detail.RealGameDetailsComponent
import features.games.list.GamesListComponent
import features.games.list.RealGamesListComponent
import features.home.HomeComponent
import features.home.RealHomeComponent
import features.login.LoginComponent
import features.login.RealLoginComponent
import features.orders.RealOrdersComponent
import features.register.RealRegisterComponent
import features.root.login.RegisterComponent
import features.users.RealUsersComponent
import features.users.UsersListComponent
import repository.remote.RemoteRepository
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalDecomposeApi::class)
internal class RealComponentFactory(
    private val mainContext: CoroutineContext,
    private val remoteRepository: RemoteRepository,
) : ComponentFactory {
    override fun createRootComponent(
        deepLink: DeepLink,
        webHistoryController: WebHistoryController?,
        componentContext: ComponentContext,
    ): RootComponent {
        return RealRootComponent(
            coroutineContext = mainContext,
            componentContext = componentContext,
            deepLink = deepLink,
            webHistoryController = webHistoryController,
            componentFactory = this,
        )
    }

    override fun createRegisterComponent(componentContext: ComponentContext): RegisterComponent {
        return RealRegisterComponent(
            componentContext = componentContext,
            coroutineContext = mainContext,
            loginRepository = remoteRepository.loginRepository(),
        )
    }

    override fun createLoginComponent(
        componentContext: ComponentContext,
        onLogin: () -> Unit,
        onRegister: () -> Unit,
    ): LoginComponent {
        return RealLoginComponent(
            coroutineContext = mainContext,
            componentContext = componentContext,
            loginRepository = remoteRepository.loginRepository(),
            onLogin = onLogin,
            onRegister = onRegister,
        )
    }

    override fun createHomeComponent(
        componentContext: ComponentContext,
        onCloseClick: () -> Unit,
        onGamesClick: (String) -> Unit,
        onAddGameClick: () -> Unit,
    ): HomeComponent {
        return RealHomeComponent(
            coroutineContext = mainContext,
            componentContext = componentContext,
            componentFactory = this,
            onClose = onCloseClick,
            onGamesClick = onGamesClick,
            onAddGameClick = onAddGameClick,
            user = DI.currentUser,
        )
    }

    override fun createGamesListComponent(
        componentContext: ComponentContext,
        onDetails: (String) -> Unit,
        onAdd: () -> Unit,
    ): GamesListComponent {
        return RealGamesListComponent(
            componentContext,
            mainContext,
            remoteRepository.gamesRepository(),
            onDetails,
            onAdd,
            DI.currentUser,
        )
    }

    override fun createGameDetailsComponent(
        componentContext: ComponentContext,
        gameId: String,
        onBackClick: () -> Unit,
        onEditClick: () -> Unit,
    ): GameDetailsComponent {
        return RealGameDetailsComponent(
            componentContext,
            mainContext,
            remoteRepository.gamesRepository(),
            gameId,
            onBackClick,
            onEditClick,
            DI.currentUser,
        )
    }

    override fun createAddGameComponent(
        componentContext: ComponentContext,
        gameId: String?,
        onBackClick: () -> Unit,
        onAddGameClick: () -> Unit,
    ): AddGameComponent {
        return RealAddGameComponent(
            componentContext,
            mainContext,
            remoteRepository.gamesRepository(),
            gameId,
            onBackClick,
            onAddGameClick,
        )
    }

    override fun createOrdersComponent(componentContext: ComponentContext): OrdersComponent {
        return RealOrdersComponent(componentContext, mainContext)
    }

    override fun createUsersListComponent(componentContext: ComponentContext): UsersListComponent {
        return RealUsersComponent(
            componentContext,
            mainContext,
            remoteRepository.usersRepository(),
        )
    }
}
