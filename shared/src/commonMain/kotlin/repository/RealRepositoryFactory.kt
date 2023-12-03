package repository

import dev.michalkonkel.gameshop.repository.GamesRepository
import repository.games.HttpGamesRepository

class RealRepositoryFactory : RepositoryFactory {
    override fun gamesRepository(): GamesRepository {
        return HttpGamesRepository()
    }
}