package dev.michalkonkel.gameshop.features.users.data.dao

import dev.michalkonkel.gameshop.database.dbQuery
import dev.michalkonkel.gameshop.database.tables.RoleEntity
import dev.michalkonkel.gameshop.database.tables.UserEntity
import dev.michalkonkel.gameshop.database.tables.Users
import dev.michalkonkel.gameshop.domain.roles.Role
import dev.michalkonkel.gameshop.domain.user.User
import dev.michalkonkel.gameshop.domain.user.UserRequest
import dev.michalkonkel.gameshop.features.roles.RolesDAOFacade
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import java.util.*

class RealUsersDAOFacade(private val rolesDAOFacade: RolesDAOFacade) : UsersDAOFacade {
    override suspend fun createUser(userRequest: UserRequest) = dbQuery {
        val userRoleId = requireNotNull(rolesDAOFacade.getIdByRole(Role.USER))

        UserEntity.new {
            name = userRequest.name
            username = userRequest.username
            password = userRequest.password
            date_created = Clock.System.now().toLocalDateTime(TimeZone.UTC).date.toString()
            role = RoleEntity[userRoleId]
        }.toUser()
    }

    override suspend fun getUsers(): List<User> = dbQuery {
        UserEntity.all().map { it.toUser() }
    }

    override suspend fun getUserByUsernameAndPassword(username: String, password: String): User? = dbQuery {
        UserEntity.find { (Users.username eq username) and (Users.password eq password) }
            .limit(1)
            .firstOrNull()
            ?.toUser()
    }

    override suspend fun existById(id: String): Boolean = dbQuery {
        UserEntity.find { Users.id eq UUID.fromString(id) }.limit(1).count() > 0
    }

    override suspend fun existByName(username: String) = dbQuery {
        UserEntity.find { Users.username eq username }.limit(1).count() > 0
    }

    private fun UserEntity.toUser(): User {
        return User(
            id = id.value.toString(),
            name = name,
            username = username,
            role = Role.entries.first { it.name.uppercase() == role.name.uppercase() }
        )
    }
}