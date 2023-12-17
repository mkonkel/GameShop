package dev.michalkonkel.gameshop.database.tables

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Roles : IntIdTable() {
    val name = varchar("name", 128)
}

class RoleEntity(
    id: EntityID<Int>,
) : IntEntity(id) {
    companion object : IntEntityClass<RoleEntity>(Roles)

    var name by Roles.name
}
