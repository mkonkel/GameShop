package features.factory

import org.koin.dsl.module

internal val componentFactoryModule =
    module {
        single<ComponentFactory> { params ->
            RealComponentFactory(
                remoteRepository = get(),
            )
        }
    }
