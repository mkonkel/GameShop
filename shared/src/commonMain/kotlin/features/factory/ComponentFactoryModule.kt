package features.factory

import org.koin.dsl.module

internal val componentFactoryModule =
    module {
        single<ComponentFactory> {
            RealComponentFactory(
                remoteRepository = get(),
                userHolder = get(),
            )
        }
    }
