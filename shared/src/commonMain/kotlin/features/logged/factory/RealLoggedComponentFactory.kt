package features.logged.factory

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.webhistory.WebHistoryController
import deeplink.DeepLink
import features.logged.LoggedComponent
import features.logged.LoggedNavigationRouter
import features.logged.RealLoggedComponent
import features.logged.games.GamesComponent
import features.logged.games.RealGamesComponent
import features.logged.users.RealUsersComponent
import features.logged.users.UsersComponent
import repository.remote.RemoteRepositoryFactory
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalDecomposeApi::class)
internal class RealLoggedComponentFactory(
    private val mainContext: CoroutineContext,
    private val repositoryFactory: RemoteRepositoryFactory,
) : LoggedComponentFactory {
    private val navigationRouter: LoggedNavigationRouter = LoggedNavigationRouter()

    override fun createLoggedComponent(
        deepLink: DeepLink,
        webHistoryController: WebHistoryController?,
        componentContext: ComponentContext,
    ): LoggedComponent {
        return RealLoggedComponent(
            coroutineContext = mainContext,
            componentContext = componentContext,
            deepLink = deepLink,
            webHistoryController = webHistoryController,
            loggedNavigationRouter = navigationRouter,
            componentFactory = this,
        )
    }

    override fun createGamesComponent(componentContext: ComponentContext): GamesComponent {
        return RealGamesComponent(componentContext, mainContext)
    }

    override fun createUsersComponent(componentContext: ComponentContext): UsersComponent {
        return RealUsersComponent(componentContext, mainContext)
    }
}
