package dev.michalkonkel.gameshop.database

import dev.michalkonkel.gameshop.database.tables.Games
import kotlinx.coroutines.Dispatchers
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import dev.michalkonkel.gameshop.database.tables.Users

class RealDatabaseFactory : DatabaseFactory {
    private companion object {
        const val driverClassName = "org.h2.Driver"
        const val jdbcURL = "jdbc:h2:file:./build/db"
    }

    override fun create() {
        Database.connect(jdbcURL, driverClassName)
        SchemaDefinition.createSchema()
    }

    private object SchemaDefinition {
        fun createSchema() {
            transaction {
                SchemaUtils.create(Users)
                SchemaUtils.create(Games)

                Users.insert {
                    it[name] = "Admin"
                    it[username] = "admin"
                    it[password] = "pass"
                    it[date_created] = Clock.System.now().toLocalDateTime(TimeZone.UTC).date.toString()
                }
            }
        }
    }
}

suspend fun <T> dbQuery(block: suspend () -> T): T =
    newSuspendedTransaction(Dispatchers.IO) { block() }