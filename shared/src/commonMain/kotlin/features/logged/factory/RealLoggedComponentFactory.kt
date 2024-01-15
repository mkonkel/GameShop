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
import features.logged.users.UsersComponent
import repository.remote.RemoteRepositoryFactory
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalDecomposeApi::class)
internal class RealLoggedComponentFactory(
    private val componentContext: ComponentContext,
    private val mainContext: CoroutineContext,
    private val repositoryFactory: RemoteRepositoryFactory,
) : LoggedComponentFactory {
    private val navigationRouter: LoggedNavigationRouter = LoggedNavigationRouter()

    override fun createLoggedComponent(
        deepLink: DeepLink,
        webHistoryController: WebHistoryController?,
    ): LoggedComponent {
        return RealLoggedComponent(
            coroutineContext = mainContext,
            componentContext = componentContext,
            deepLink = deepLink,
            webHistoryController = webHistoryController,
            rootNavigationRouter = navigationRouter,
            componentFactory = this,
        )
    }

    override fun createGamesComponent(): GamesComponent {
        return RealGamesComponent(componentContext)
    }

    override fun createUsersComponent(): UsersComponent {
        TODO("Not yet implemented")
    }
}
