import com.arkivanov.decompose.ExperimentalDecomposeApi
import features.RealRootComponent
import features.RootComponent
import features.factory.componentFactoryModule
import org.koin.dsl.module
import repository.repositoryModule

@OptIn(ExperimentalDecomposeApi::class)
val sharedModule =
    module {
        factory<RootComponent> { params ->
            RealRootComponent(
                deepLink = params.get(),
                webHistoryController = params.getOrNull(),
                componentContext = params.get(),
                componentFactory = get(),
            )
        }

        includes(repositoryModule, componentFactoryModule)
    }
