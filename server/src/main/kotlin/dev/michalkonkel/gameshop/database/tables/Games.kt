package dev.michalkonkel.gameshop.database.tables

import org.jetbrains.exposed.dao.id.UUIDTable

object Games : UUIDTable() {
    val name = varchar("name", 128)
    val description = varchar("description", 128)
    val price = varchar("price", 128)
}