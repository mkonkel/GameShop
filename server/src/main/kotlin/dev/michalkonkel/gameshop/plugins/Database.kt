package dev.michalkonkel.gameshop.plugins

import io.ktor.server.application.Application
import org.koin.ktor.ext.inject
import dev.michalkonkel.gameshop.database.DatabaseFactory

fun Application.configureDatabase() {
    val databaseFactory by inject<DatabaseFactory>()
    databaseFactory.create()
}
