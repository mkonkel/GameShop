package dev.michalkonkel.gameshop.repository.users

import dev.michalkonkel.gameshop.domain.user.User
import dev.michalkonkel.gameshop.domain.user.UserRequest

interface UsersRepository {
    suspend fun addUser(request: UserRequest): User?

    suspend fun getUsers(): List<User>

    companion object {
        const val USERS_ENDPOINT = "/users"
    }
}
