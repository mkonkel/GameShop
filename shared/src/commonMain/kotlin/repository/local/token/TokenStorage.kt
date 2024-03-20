package repository.local.token

import io.ktor.client.plugins.auth.providers.BearerTokens

internal interface TokenStorage {
    fun putTokens(
        accessToken: String,
        refreshToken: String,
    )

    fun getToken(): BearerTokens
}
