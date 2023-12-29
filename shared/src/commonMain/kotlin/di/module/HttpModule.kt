package di.module

import repository.local.TokenStorage
import repository.remote.client.HttpClientFactory

internal class HttpModule(private val tokenStorage: TokenStorage) {
    val httpClientFactory: HttpClientFactory by lazy { HttpClientFactory(tokenStorage) }
}
