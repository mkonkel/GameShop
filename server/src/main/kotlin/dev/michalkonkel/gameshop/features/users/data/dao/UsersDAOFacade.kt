package dev.michalkonkel.gameshop.features.users.data.dao

import dev.michalkonkel.gameshop.domain.user.User
import dev.michalkonkel.gameshop.domain.user.UserRequest

interface UsersDAOFacade {
    suspend fun createUser(userRequest: UserRequest): User?

    suspend fun getUsers(): List<User>

    suspend fun getUserByUsernameAndPassword(
        username: String,
        password: String,
    ): User?

    suspend fun existById(id: String): Boolean

    suspend fun existByName(username: String): Boolean
}
