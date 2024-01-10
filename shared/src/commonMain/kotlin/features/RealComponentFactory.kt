package features

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.webhistory.WebHistoryController
import deeplink.DeepLink
import features.games.GamesComponent
import features.games.RealGamesComponent
import features.login.LoginComponent
import features.login.RealLoginComponent
import features.root.RealRootComponent
import features.root.RootComponent
import features.users.RealUsersComponent
import features.users.UsersComponent
import repository.remote.RemoteRepositoryFactory
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalDecomposeApi::class)
internal class RealComponentFactory(
    private val componentContext: ComponentContext,
    private val mainContext: CoroutineContext,
    private val repositoryFactory: RemoteRepositoryFactory
) : ComponentFactory {
    override fun createRootComponent(
        deepLink: DeepLink,
        webHistoryController: WebHistoryController?,
    ): RootComponent {
        return RealRootComponent(
            componentContext = componentContext,
            deepLink = deepLink,
            webHistoryController = webHistoryController,
            componentFactory = this
        )
    }

    override fun createUsersComponent(): UsersComponent {
        return RealUsersComponent(componentContext)
    }

    override fun createLoginComponent(): LoginComponent {
        return RealLoginComponent(
            componentContext = componentContext,
            loginRepository = repositoryFactory.loginRepository()
        ).also { it.attach(mainContext) }
    }

    override fun createGamesComponent(): GamesComponent {
        return RealGamesComponent(componentContext)
    }
}