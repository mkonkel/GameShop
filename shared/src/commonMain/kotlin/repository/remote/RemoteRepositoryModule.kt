package repository.remote

import org.koin.dsl.module
import repository.remote.client.HttpClientFactory
import repository.remote.client.RealHttpClientFactory

internal val remoteRepositoryModule =
    module {
        single<HttpClientFactory> { RealHttpClientFactory(get()) }

        single<RemoteRepository> {
            RealRemoteRepository(
                clientFactory = get(),
                tokenStorage = get(),
            )
        }
    }
