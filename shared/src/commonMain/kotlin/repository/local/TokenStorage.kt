package repository.local

import io.ktor.client.plugins.auth.providers.BearerTokens

internal interface TokenStorage {
    fun putTokens(
        accessToken: String,
        refreshToken: String,
    )

    fun getToken(): BearerTokens
}
