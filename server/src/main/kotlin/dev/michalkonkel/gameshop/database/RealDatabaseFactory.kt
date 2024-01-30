package dev.michalkonkel.gameshop.database

import dev.michalkonkel.gameshop.database.tables.Games
import dev.michalkonkel.gameshop.database.tables.Roles
import dev.michalkonkel.gameshop.database.tables.Users
import kotlinx.coroutines.Dispatchers
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

                addDefaultUsers()
                addDefaultGames()
            }
        }
    }
}

suspend fun <T> dbQuery(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
