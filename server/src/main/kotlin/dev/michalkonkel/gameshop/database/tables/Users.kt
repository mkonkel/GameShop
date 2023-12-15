package dev.michalkonkel.gameshop.database.tables

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption
import java.util.*

object Users : UUIDTable() {
    val name = varchar("name", 128)
    val date_created = varchar("date_created", 10)
    val username = varchar("username", 128)
    val password = varchar("password", 128)
    val role = reference("role_id", Roles, onDelete = ReferenceOption.CASCADE).index()
}

class UserEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<UserEntity>(Users)

    var name by Users.name
    var date_created by Users.date_created
    var username by Users.username
    var password by Users.password
    var role by RoleEntity referencedOn Users.role
}