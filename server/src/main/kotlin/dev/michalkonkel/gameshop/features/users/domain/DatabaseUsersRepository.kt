package dev.michalkonkel.gameshop.features.users.domain

import dev.michalkonkel.gameshop.domain.user.User
import dev.michalkonkel.gameshop.repository.users.UsersRepository

interface DatabaseUsersRepository : UsersRepository {
    suspend fun getUserByUsernameAndPassword(
        username: String,
        password: String,
    ): User?

    suspend fun existById(id: String): Boolean

    suspend fun existByName(username: String): Boolean
}
