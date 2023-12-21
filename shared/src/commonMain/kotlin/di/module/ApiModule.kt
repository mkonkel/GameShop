package di.module

import repository.local.RealTokenStorage
import repository.local.TokenStorage
import repository.remote.api.ApiFactory
import repository.remote.api.RealApiFactory
import repository.remote.api.client.HttpClientFactory

internal class ApiModule(
    private val tokenStorage: TokenStorage
) {
    private val httpClientFactory: HttpClientFactory by lazy { HttpClientFactory(tokenStorage) }

    val apiFactory: ApiFactory by lazy {
        RealApiFactory(
            client = httpClientFactory.client,
            baseUrl = "0.0.0.0:8080"
        )
    }
}