package repository.remote.client

import io.ktor.client.HttpClient

internal interface HttpClientFactory {
    fun create(): HttpClient
}
