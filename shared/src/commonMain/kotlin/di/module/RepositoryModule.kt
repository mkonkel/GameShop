package di.module

import io.ktor.client.HttpClient
import repository.local.TokenStorage
import repository.remote.RealRemoteRepositoryFactory
import repository.remote.RemoteRepositoryFactory

internal class RepositoryModule(
    private val tokenStorage: TokenStorage = StorageModule().tokenStorage,
    private val httpClient: HttpClient = HttpModule(tokenStorage).httpClientFactory.client,
) {
    val remoteRepositoryFactory: RemoteRepositoryFactory by lazy {
        RealRemoteRepositoryFactory(httpClient, tokenStorage)
    }
}
