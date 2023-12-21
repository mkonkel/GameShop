package repository.remote

import dev.michalkonkel.gameshop.repository.GamesRepository

interface RemoteRepositoryFactory {
    fun gamesRepository(): GamesRepository
}
