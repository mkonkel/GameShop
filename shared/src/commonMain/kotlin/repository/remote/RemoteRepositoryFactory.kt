package repository.remote

import dev.michalkonkel.gameshop.repository.games.GamesRepository
import dev.michalkonkel.gameshop.repository.users.UsersRepository
import repository.remote.login.LoginRepository

interface RemoteRepositoryFactory {
    fun loginRepository(): LoginRepository

    fun gamesRepository(): GamesRepository

    fun usersRepository(): UsersRepository
}
