package repository.remote.client

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import repository.local.TokenStorage

internal class HttpClientFactory(
    private val tokenStorage: TokenStorage,
) {
    fun create(): HttpClient {
        return HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        isLenient = true
                        ignoreUnknownKeys = true
                        useAlternativeNames = false
                    },
                )
            }
            install(DefaultRequest) {
                url("http://localhost:3000")
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        tokenStorage.getToken()
                    }
                    refreshTokens {
                        TODO("Not implemented yet!")
                    }
                    sendWithoutRequest { request ->
                        when (request.url.pathSegments.last()) {
                            "login" -> false
                            else -> true
                        }
                    }
                }
            }
        }
    }
}
