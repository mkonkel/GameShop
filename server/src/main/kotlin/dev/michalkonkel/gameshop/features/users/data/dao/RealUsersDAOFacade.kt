package dev.michalkonkel.gameshop.features.users.data.dao

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import dev.michalkonkel.gameshop.database.dbQuery
import dev.michalkonkel.gameshop.database.tables.Users
import dev.michalkonkel.gameshop.domain.user.User
import dev.michalkonkel.gameshop.domain.user.UserRequest
import java.util.*

class RealUsersDAOFacade : UsersDAOFacade {
    override suspend fun createUser(userRequest: UserRequest) = dbQuery {
        Users.insert {
            it[name] = userRequest.name
            it[username] = userRequest.username
            it[password] = userRequest.password
            it[date_created] = Clock.System.now().toLocalDateTime(TimeZone.UTC).date.toString()
        }
            .resultedValues?.singleOrNull()?.toUser()
    }

    override suspend fun getUsers(): List<User> = dbQuery {
        Users.selectAll().map { it.toUser() }
    }

    override suspend fun getUserByUsernameAndPassword(username: String, password: String): User? = dbQuery {
        Users.select { (Users.username eq username) and (Users.password eq password) }
            .limit(1)
            .firstOrNull()
            ?.toUser()
    }

    override suspend fun existById(id: String): Boolean = dbQuery {
        Users.select { Users.id eq UUID.fromString(id) }.limit(1).count() > 0
    }

    override suspend fun existByName(username: String) = dbQuery {
        Users.select { Users.username eq username }.limit(1).count() > 0
    }

    private fun ResultRow.toUser(): User {
        return this.let {
            User(
                id = it[Users.id].toString(),
                name = it[Users.name],
                username = it[Users.username]
            )
        }
    }
}