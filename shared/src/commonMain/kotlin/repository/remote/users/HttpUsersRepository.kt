package repository.remote.users

import dev.michalkonkel.gameshop.domain.user.User
import dev.michalkonkel.gameshop.domain.user.UserRequest
import dev.michalkonkel.gameshop.repository.users.UsersRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

internal class HttpUsersRepository(private val client: HttpClient) : UsersRepository {
    override suspend fun addUser(request: UserRequest): User? {
        return client.post(UsersRepository.USERS_ENDPOINT) { setBody(request) }.body()
    }

    override suspend fun getUsers(): List<User> {
        return client.get(UsersRepository.USERS_ENDPOINT).body()
    }
}
