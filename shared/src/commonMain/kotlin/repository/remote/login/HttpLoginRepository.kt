package repository.remote.login

import dev.michalkonkel.gameshop.domain.login.LoginRequest
import dev.michalkonkel.gameshop.domain.login.LoginResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import repository.local.token.TokenStorage

internal class HttpLoginRepository(
    private val client: HttpClient,
    private val tokenStorage: TokenStorage,
) : LoginRepository {
    override suspend fun login(
        username: String,
        password: String,
    ): LoginResponse? {
        val request = LoginRequest(username, password)

        return client.post("/login") { setBody(request) }.body<LoginResponse?>()
            .also {
                if (it != null) {
                    tokenStorage.putTokens(it.token, "NOT IMPLEMENTED")
                }
            }
    }
}
