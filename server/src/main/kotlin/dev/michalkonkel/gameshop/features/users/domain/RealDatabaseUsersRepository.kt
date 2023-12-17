package dev.michalkonkel.gameshop.features.users.domain

import dev.michalkonkel.gameshop.domain.user.User
import dev.michalkonkel.gameshop.domain.user.UserRequest
import dev.michalkonkel.gameshop.features.users.data.dao.UsersDAOFacade

internal class RealDatabaseUsersRepository(
    private val dao: UsersDAOFacade,
) : DatabaseUsersRepository {
    override suspend fun addUser(userRequest: UserRequest): User? = dao.createUser(userRequest)

    override suspend fun getUsers(): List<User> = dao.getUsers()

    override suspend fun getUserByUsernameAndPassword(
        username: String,
        password: String,
    ): User? = dao.getUserByUsernameAndPassword(username, password)

    override suspend fun existById(id: String): Boolean = dao.existById(id)

    override suspend fun existByName(username: String): Boolean = dao.existByName(username)
}
