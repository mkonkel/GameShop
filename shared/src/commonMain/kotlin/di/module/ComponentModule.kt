package di.module

import features.logged.factory.LoggedComponentFactory
import features.logged.factory.RealLoggedComponentFactory
import features.root.factory.RealRootComponentFactory
import features.root.factory.RootComponentFactory
import repository.remote.RemoteRepositoryFactory
import kotlin.coroutines.CoroutineContext

class ComponentModule(
    private val mainContext: CoroutineContext,
    private val remoteRepositoryFactory: RemoteRepositoryFactory,
) {
    private val loggedComponentFactory: LoggedComponentFactory by lazy {
        RealLoggedComponentFactory(mainContext, remoteRepositoryFactory)
    }

    val rootComponentFactory: RootComponentFactory by lazy {
        RealRootComponentFactory(mainContext, remoteRepositoryFactory) { loggedComponentFactory }
    }
}
