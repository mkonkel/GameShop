package dev.michalkonkel.gameshop.database.tables

import org.jetbrains.exposed.dao.id.UUIDTable

object Users : UUIDTable() {
    val name = varchar("name", 128)
    val date_created = varchar("date_created", 10)
    val username = varchar("username", 128)
    val password = varchar("password", 128)
}