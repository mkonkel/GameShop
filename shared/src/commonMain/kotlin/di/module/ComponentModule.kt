package di.module

import features.logged.factory.LoggedComponentFactory
import features.logged.factory.RealLoggedComponentFactory
import features.root.RootNavigationRouter
import features.root.factory.RealRootComponentFactory
import features.root.factory.RootComponentFactory
import repository.remote.RemoteRepositoryFactory
import kotlin.coroutines.CoroutineContext

class ComponentModule(
    private val mainContext: CoroutineContext,
    private val remoteRepositoryFactory: RemoteRepositoryFactory,
) {
    private val navigationRouter: RootNavigationRouter = RootNavigationRouter()

    private val loggedComponentFactory: LoggedComponentFactory by lazy {
        RealLoggedComponentFactory(
            mainContext = mainContext,
            repositoryFactory = remoteRepositoryFactory,
            rootNavigationRouter = navigationRouter,
        )
    }

    val rootComponentFactory: RootComponentFactory by lazy {
        RealRootComponentFactory(
            mainContext = mainContext,
            repositoryFactory = remoteRepositoryFactory,
            rootNavigationRouter = navigationRouter,
            loggedComponentFactory = { loggedComponentFactory },
        )
    }
}
