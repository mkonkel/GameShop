package features.logged.factory

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.webhistory.WebHistoryController
import deeplink.DeepLink
import features.logged.HomeComponent
import features.logged.RealHomeComponent
import features.logged.games.detail.GameDetailsComponent
import features.logged.games.detail.RealGameDetailsComponent
import features.logged.games.list.GamesListComponent
import features.logged.games.list.RealGamesListComponent
import features.logged.users.RealUsersComponent
import features.logged.users.UsersListComponent
import features.root.RootNavigationRouter
import repository.remote.RemoteRepositoryFactory
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalDecomposeApi::class)
internal class RealLoggedComponentFactory(
    private val mainContext: CoroutineContext,
    private val repositoryFactory: RemoteRepositoryFactory,
    private val rootNavigationRouter: RootNavigationRouter,
) : LoggedComponentFactory {
    override fun createLoggedComponent(
        deepLink: DeepLink,
        webHistoryController: WebHistoryController?,
        componentContext: ComponentContext,
    ): HomeComponent {
        return RealHomeComponent(
            coroutineContext = mainContext,
            componentContext = componentContext,
            deepLink = deepLink,
            webHistoryController = webHistoryController,
            componentFactory = this,
        )
    }

    override fun createGamesListComponent(componentContext: ComponentContext): GamesListComponent {
        return RealGamesListComponent(componentContext, mainContext, rootNavigationRouter)
    }

    override fun createGameDetailsComponent(componentContext: ComponentContext): GameDetailsComponent {
        return RealGameDetailsComponent(componentContext, mainContext)
    }

    override fun createUsersListComponent(componentContext: ComponentContext): UsersListComponent {
        return RealUsersComponent(componentContext, mainContext)
    }
}
