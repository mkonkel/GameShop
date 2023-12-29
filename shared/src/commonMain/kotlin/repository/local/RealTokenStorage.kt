package repository.local

import io.ktor.client.plugins.auth.providers.BearerTokens

internal class RealTokenStorage : TokenStorage {
    private val tokens = mutableSetOf<BearerTokens>()

    override fun putTokens(
        accessToken: String,
        refreshToken: String,
    ) {
        tokens.add(BearerTokens(accessToken, refreshToken))
    }

    override fun getToken(): BearerTokens {
        return tokens.last()
    }
}
