package dev.michalkonkel.gameshop.database

import dev.michalkonkel.gameshop.database.tables.Games
import dev.michalkonkel.gameshop.database.tables.RoleEntity
import dev.michalkonkel.gameshop.database.tables.Roles
import dev.michalkonkel.gameshop.database.tables.UserEntity
import dev.michalkonkel.gameshop.database.tables.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

class RealDatabaseFactory : DatabaseFactory {
    private companion object {
        const val DRIVER_CLASS_NAME = "org.h2.Driver"
        const val JDBC_URL = "jdbc:h2:file:./build/db"
    }

    override fun create() {
        Database.connect(JDBC_URL, DRIVER_CLASS_NAME)
        SchemaDefinition.createSchema()
    }

    private object SchemaDefinition {
        fun createSchema() {
            transaction {
                SchemaUtils.create(Roles)
                SchemaUtils.create(Users)
                SchemaUtils.create(Games)

                val adminRole = RoleEntity.new { name = "admin" }
                val userRole = RoleEntity.new { name = "user" }

                UserEntity.new {
                    name = "Admin"
                    username = "admin"
                    password = "pass"
                    dateCreated =
                        Clock
                            .System
                            .now()
                            .toLocalDateTime(TimeZone.UTC)
                            .date
                            .toString()
                    role = adminRole
                }

                UserEntity.new {
                    name = "Admin"
                    username = "user"
                    password = "pass"
                    dateCreated =
                        Clock
                            .System
                            .now()
                            .toLocalDateTime(TimeZone.UTC)
                            .date
                            .toString()
                    role = userRole
                }
            }
        }
    }
}

suspend fun <T> dbQuery(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
