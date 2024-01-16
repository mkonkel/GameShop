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
import features.root.login.RealLoginComponent
import features.root.login.RegisterComponent
import repository.remote.RemoteRepositoryFactory
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalDecomposeApi::class)
internal class RealRootComponentFactory(
    private val mainContext: CoroutineContext,
    private val repositoryFactory: RemoteRepositoryFactory,
    private val rootNavigationRouter: RootNavigationRouter,
    private val loggedComponentFactory: () -> LoggedComponentFactory,
) : RootComponentFactory {
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
            rootNavigationRouter = rootNavigationRouter,
            componentFactory = this,
            loggedComponentFactory = loggedComponentFactory,
        )
    }

    override fun createRegisterComponent(componentContext: ComponentContext): RegisterComponent {
        TODO("Not yet implemented")
    }

    override fun createLoginComponent(componentContext: ComponentContext): LoginComponent {
        return RealLoginComponent(
            coroutineContext = mainContext,
            componentContext = componentContext,
            loginRepository = repositoryFactory.loginRepository(),
            rootNavigationRouter = rootNavigationRouter,
        )
    }
}
