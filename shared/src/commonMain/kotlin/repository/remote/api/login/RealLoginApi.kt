package repository.remote.api.login

import dev.michalkonkel.gameshop.domain.login.LoginRequest
import dev.michalkonkel.gameshop.domain.login.LoginResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody

internal class RealLoginApi(
    private val client: HttpClient,
    baseUrl: String
) : LoginApi {
    private val loginUrl = "$baseUrl/login"
    override suspend fun login(username: String, password: String): LoginResponse? {
        val request = LoginRequest(username, password)

        return client.post(loginUrl) { setBody(request) }.body()
    }
}
