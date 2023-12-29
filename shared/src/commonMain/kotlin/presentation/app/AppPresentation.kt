package presentation.app

import dev.michalkonkel.gameshop.domain.user.User

interface AppPresentation {
    suspend fun getUsers(): List<User>
}
