package features.root.factory

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.webhistory.WebHistoryController
import deeplink.DeepLink
import features.logged.factory.LoggedComponentFactory
import features.root.RealRootComponent
import features.root.RootComponent
import features.root.RootNavigationRouter
import features.root.login.LoginComponent
import features.root.login.RealLoggedComponent
import features.root.login.RegisterComponent
import repository.remote.RemoteRepositoryFactory
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalDecomposeApi::class)
internal class RealRootComponentFactory(
    private val componentContext: ComponentContext,
    private val mainContext: CoroutineContext,
    private val repositoryFactory: RemoteRepositoryFactory,
    private val loggedComponentFactory: () -> LoggedComponentFactory,
) : RootComponentFactory {
    private val navigationRouter: RootNavigationRouter = RootNavigationRouter()

    override fun createRootComponent(
        deepLink: DeepLink,
        webHistoryController: WebHistoryController?,
    ): RootComponent {
        return RealRootComponent(
            coroutineContext = mainContext,
            componentContext = componentContext,
            deepLink = deepLink,
            webHistoryController = webHistoryController,
            rootNavigationRouter = navigationRouter,
            componentFactory = this,
            loggedComponentFactory = loggedComponentFactory,
        )
    }

    override fun createRegisterComponent(): RegisterComponent {
        TODO("Not yet implemented")
    }

    override fun createLoginComponent(): LoginComponent {
        return RealLoggedComponent(
            coroutineContext = mainContext,
            componentContext = componentContext,
            rootNavigationRouter = navigationRouter,
            loginRepository = repositoryFactory.loginRepository(),
        )
    }
}
