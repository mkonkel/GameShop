package di.module

import com.arkivanov.decompose.ComponentContext
import features.logged.factory.LoggedComponentFactory
import features.logged.factory.RealLoggedComponentFactory
import features.root.factory.RealRootComponentFactory
import features.root.factory.RootComponentFactory
import repository.remote.RemoteRepositoryFactory
import kotlin.coroutines.CoroutineContext

class ComponentModule(
    private val componentContext: ComponentContext,
    private val mainContext: CoroutineContext,
    private val remoteRepositoryFactory: RemoteRepositoryFactory,
) {
    private val loggedComponentFactory: LoggedComponentFactory by lazy {
        RealLoggedComponentFactory(componentContext, mainContext, remoteRepositoryFactory)
    }

    val rootComponentFactory: RootComponentFactory by lazy {
        RealRootComponentFactory(componentContext, mainContext, remoteRepositoryFactory) { loggedComponentFactory }
    }
}
