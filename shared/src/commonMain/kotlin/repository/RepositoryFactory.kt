package repository

import dev.michalkonkel.gameshop.repository.GamesRepository

interface RepositoryFactory {
    fun gamesRepository(): GamesRepository
}
