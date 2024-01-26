package dev.michalkonkel.gameshop.database.tables

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.UUID

object Games : UUIDTable() {
    val name = varchar("name", 128)
    val description = text("description")
    val price = varchar("price", 128)
    val imageUrl = varchar("price", 255)
}

class GamesEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<GamesEntity>(Games)

    var name by Games.name
    var description by Games.description
    var price by Games.price
    var imageUrl by Games.imageUrl
}
