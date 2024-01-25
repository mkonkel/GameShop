package features.factory

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.webhistory.WebHistoryController
import deeplink.DeepLink
import features.NavigationRouter
import features.RealRootComponent
import features.RootComponent
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
    private val navigationRouter: NavigationRouter = NavigationRouter()

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
            navigationRouter = navigationRouter,
            componentFactory = this,
        )
    }

    override fun createRegisterComponent(componentContext: ComponentContext): RegisterComponent {
        return RealRegisterComponent(
            componentContext = componentContext,
            coroutineContext = mainContext,
            navigationRouter = navigationRouter,
            loginRepository = remoteRepository.loginRepository(),
        )
    }

    override fun createLoginComponent(componentContext: ComponentContext): LoginComponent {
        return RealLoginComponent(
            coroutineContext = mainContext,
            componentContext = componentContext,
            loginRepository = remoteRepository.loginRepository(),
            navigationRouter = navigationRouter,
        )
    }

    override fun createHomeComponent(componentContext: ComponentContext): HomeComponent {
        return RealHomeComponent(
            coroutineContext = mainContext,
            componentContext = componentContext,
            componentFactory = this,
        )
    }

    override fun createGamesListComponent(componentContext: ComponentContext): GamesListComponent {
        return RealGamesListComponent(componentContext, mainContext, navigationRouter)
    }

    override fun createGameDetailsComponent(componentContext: ComponentContext): GameDetailsComponent {
        return RealGameDetailsComponent(componentContext, mainContext)
    }

    override fun createOrdersComponent(componentContext: ComponentContext): OrdersComponent {
        return RealOrdersComponent(componentContext, mainContext)
    }

    override fun createUsersListComponent(componentContext: ComponentContext): UsersListComponent {
        return RealUsersComponent(componentContext, mainContext)
    }
}
