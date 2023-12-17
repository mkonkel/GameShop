package dev.michalkonkel.gameshop.features.roles

import dev.michalkonkel.gameshop.database.dbQuery
import dev.michalkonkel.gameshop.database.tables.RoleEntity
import dev.michalkonkel.gameshop.database.tables.Roles
import dev.michalkonkel.gameshop.domain.roles.Role
import org.jetbrains.exposed.sql.upperCase

class RealRolesDAOFacade : RolesDAOFacade {
    override suspend fun getRoles() =
        dbQuery {
            RoleEntity.all().map { it.toRole() }
        }

    override suspend fun getIdByRole(role: Role) =
        dbQuery {
            RoleEntity
                .find { (Roles.name.upperCase() eq role.name.uppercase()) }
                .limit(1)
                .first()
                .id
                .value
        }

    override suspend fun getRoleById(id: Int) =
        dbQuery {
            RoleEntity
                .find { (Roles.id eq id) }
                .limit(1)
                .firstOrNull()
                ?.toRole()
        }

    private fun RoleEntity.toRole(): Role = Role.entries.first { it.name.uppercase() == name.uppercase() }
}
