package dev.michalkonkel.gameshop.features.roles

import dev.michalkonkel.gameshop.domain.roles.Role

interface RolesDAOFacade {
    suspend fun getRoles(): List<Role>
    suspend fun getIdByRole(role: Role): Int?
    suspend fun getRoleById(id: Int): Role?

}