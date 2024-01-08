package di.module

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import features.ComponentFactory
import features.RealComponentFactory
import repository.remote.RemoteRepositoryFactory

@OptIn(ExperimentalDecomposeApi::class)
class ComponentModule(
    private val componentContext: ComponentContext,
    private val remoteRepositoryFactory: RemoteRepositoryFactory
) {
    val componentFactory: ComponentFactory by lazy {
        RealComponentFactory(componentContext, remoteRepositoryFactory)
    }
}