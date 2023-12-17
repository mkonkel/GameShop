package dev.michalkonkel.gameshop.plugins

import dev.michalkonkel.gameshop.database.DatabaseFactory
import io.ktor.server.application.Application
import org.koin.ktor.ext.inject

fun Application.configureDatabase() {
    val databaseFactory by inject<DatabaseFactory>()
    databaseFactory.create()
}
