package dev.michalkonkel.gameshop

import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    embeddedServer(Netty, port = 3000) {
        createDatabase()

        install(CORS) {
            allowCredentials = true
            allowSameOrigin = true

            anyHost()

            allowMethod(HttpMethod.Options)
            allowMethod(HttpMethod.Put)
            allowMethod(HttpMethod.Patch)
            allowMethod(HttpMethod.Delete)
            allowMethod(HttpMethod.Post)
            allowMethod(HttpMethod.Get)

            allowHeader(HttpHeaders.ContentType)
            allowHeader(HttpHeaders.Accept)
            allowHeader(HttpHeaders.Authorization)

            allowHeader(HttpHeaders.AccessControlAllowOrigin)
            allowHeader(HttpHeaders.AccessControlAllowMethods)
            allowHeader(HttpHeaders.AccessControlRequestHeaders)
            allowHeader(HttpHeaders.Origin)
        }
        install(ContentNegotiation) {
            json()
        }
        routing {
            route("/login") {
                post {
                    val dbUser = newSuspendedTransaction(Dispatchers.IO) {
                        UserTable.select { UserTable.username eq "admin" }
                            .limit(1)
                            .firstOrNull()
                            ?.let {
                                User(
                                    username = it[UserTable.id].toString(),
                                    s = it[UserTable.name].toString(),
                                    s1 = it[UserTable.password].toString(),
                                    admin = Role.ADMIN
                                )
                            }
                    }

                    call.respond(
                        status = HttpStatusCode.OK,
                        message = requireNotNull(dbUser)
                    )
                }
            }
        }
    }.start(wait = true)
}

@Serializable
data class User(val username: String, val s: String, val s1: String, val admin: String)
object Role {
    const val ADMIN = "ADMIN"
}

private fun createDatabase() {
    val DRIVER_CLASS_NAME = "org.h2.Driver"
    val JDBC_URL = "jdbc:h2:file:./build/db"

    Database.connect(JDBC_URL, DRIVER_CLASS_NAME)

    transaction {
        SchemaUtils.create(UserTable)

        UserTable.insert {
            it[name] = "Admin"
            it[username] = "admin"
            it[password] = "pass"
        }
    }
}

object UserTable : UUIDTable() {
    val name = varchar("name", 128)
    val username = varchar("username", 128)
    val password = varchar("password", 128)
}
