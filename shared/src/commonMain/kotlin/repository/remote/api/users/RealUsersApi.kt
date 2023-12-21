package repository.remote.api.users

import dev.michalkonkel.gameshop.domain.user.User
import dev.michalkonkel.gameshop.domain.user.UserRequest
import dev.michalkonkel.gameshop.repository.users.UsersRepository.Companion.USERS_ENDPOINT
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class RealUsersApi(
    private val client: HttpClient,
    baseUrl: String
) : UsersApi {
    private val usersUrl = "$baseUrl${USERS_ENDPOINT}"
    override suspend fun addUser(userRequest: UserRequest): User? {
        TODO()
    }

    override suspend fun getUsers(): List<User> {
        return client.get(usersUrl).body()
    }
}
