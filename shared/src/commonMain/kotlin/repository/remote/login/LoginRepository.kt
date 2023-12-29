package repository.remote.login

import dev.michalkonkel.gameshop.domain.login.LoginResponse

interface LoginRepository {
    suspend fun login(
        username: String,
        password: String,
    ): LoginResponse?
}
