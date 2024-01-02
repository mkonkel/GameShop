package repository.remote.login

import dev.michalkonkel.gameshop.domain.login.LoginRequest
import dev.michalkonkel.gameshop.domain.login.LoginResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import repository.local.TokenStorage

// TODO move the token storage and handle token response in UseCase
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
