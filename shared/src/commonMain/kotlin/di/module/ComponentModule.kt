package di.module

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import features.ComponentFactory
import features.RealComponentFactory
import repository.remote.RemoteRepositoryFactory
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalDecomposeApi::class)
class ComponentModule(
    private val componentContext: ComponentContext,
    private val mainContext: CoroutineContext,
    private val remoteRepositoryFactory: RemoteRepositoryFactory
) {
    val componentFactory: ComponentFactory by lazy {
        RealComponentFactory(componentContext, mainContext, remoteRepositoryFactory)
    }
}