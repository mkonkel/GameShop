package features.factory

import com.arkivanov.decompose.ComponentContext
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
import repository.local.user.UserHolder
import repository.remote.RemoteRepository

internal class RealComponentFactory(
    private val remoteRepository: RemoteRepository,
    private val userHolder: UserHolder,
) : ComponentFactory {
    override fun createRegisterComponent(componentContext: ComponentContext): RegisterComponent {
        return RealRegisterComponent(
            componentContext = componentContext,
            loginRepository = remoteRepository.loginRepository(),
        )
    }

    override fun createLoginComponent(
        componentContext: ComponentContext,
        onLogin: () -> Unit,
        onRegister: () -> Unit,
    ): LoginComponent {
        return RealLoginComponent(
            componentContext = componentContext,
            loginRepository = remoteRepository.loginRepository(),
            onLogin = onLogin,
            onRegister = onRegister,
            userHolder = userHolder,
        )
    }

    override fun createHomeComponent(
        componentContext: ComponentContext,
        onCloseClick: () -> Unit,
        onGamesClick: (String) -> Unit,
        onAddGameClick: () -> Unit,
    ): HomeComponent {
        return RealHomeComponent(
            componentContext = componentContext,
            componentFactory = this,
            onClose = onCloseClick,
            onGamesClick = onGamesClick,
            onAddGameClick = onAddGameClick,
            user = userHolder.user,
        )
    }

    override fun createGamesListComponent(
        componentContext: ComponentContext,
        onDetails: (String) -> Unit,
        onAdd: () -> Unit,
    ): GamesListComponent {
        return RealGamesListComponent(
            componentContext = componentContext,
            gamesRepository = remoteRepository.gamesRepository(),
            onGameDetails = onDetails,
            onAddGame = onAdd,
            user = userHolder.user,
        )
    }

    override fun createGameDetailsComponent(
        componentContext: ComponentContext,
        gameId: String,
        onBackClick: () -> Unit,
        onEditClick: () -> Unit,
    ): GameDetailsComponent {
        return RealGameDetailsComponent(
            componentContext = componentContext,
            gamesRepository = remoteRepository.gamesRepository(),
            gameId = gameId,
            onBackClick = onBackClick,
            onEditClick = onEditClick,
            user = userHolder.user,
        )
    }

    override fun createAddGameComponent(
        componentContext: ComponentContext,
        gameId: String?,
        onBackClick: () -> Unit,
        onAddGameClick: () -> Unit,
    ): AddGameComponent {
        return RealAddGameComponent(
            componentContext = componentContext,
            gamesRepository = remoteRepository.gamesRepository(),
            gameId = gameId,
            onBackClick = onBackClick,
            onAddGameClick = onAddGameClick,
        )
    }

    override fun createOrdersComponent(componentContext: ComponentContext): OrdersComponent {
        return RealOrdersComponent(componentContext)
    }

    override fun createUsersListComponent(componentContext: ComponentContext): UsersListComponent {
        return RealUsersComponent(
            componentContext,
            remoteRepository.usersRepository(),
        )
    }
}
