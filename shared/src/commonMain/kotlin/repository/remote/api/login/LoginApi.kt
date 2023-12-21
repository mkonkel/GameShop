package repository.remote.api.login

import dev.michalkonkel.gameshop.domain.login.LoginResponse

interface LoginApi {
    suspend fun login(username: String, password: String): LoginResponse?
}