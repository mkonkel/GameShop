package repository.remote.api.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import kotlinx.serialization.json.Json
import repository.local.TokenStorage

internal class HttpClientFactory(
    private val tokenStorage: TokenStorage
) {
    companion object {
        private const val TOKEN = "token"
        private const val REFRESH_TOKEN = "refresh_token"
    }

    val client by lazy {
        HttpClient {
            install(ContentNegotiation) {
                Json {
                    isLenient = true;
                    ignoreUnknownKeys = true;
                    useAlternativeNames = false
                }
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.INFO
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        BearerTokens("Bearer", requireNotNull(tokenStorage.get(TOKEN)))
                    }
                    refreshTokens {
                        BearerTokens("Bearer", requireNotNull(tokenStorage.get(REFRESH_TOKEN)))
                        TODO("Call for refreshing token")
                    }
                    sendWithoutRequest { request ->
                        request.url.pathSegments.contains("/login")
                    }
                }
            }
        }
    }
}