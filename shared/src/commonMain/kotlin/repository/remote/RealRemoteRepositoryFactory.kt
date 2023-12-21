package repository.remote

import dev.michalkonkel.gameshop.repository.GamesRepository
import repository.remote.api.ApiFactory
import repository.remote.games.HttpGamesRepository

class RealRemoteRepositoryFactory(private val apiFactory: ApiFactory) : RemoteRepositoryFactory {
    override fun gamesRepository(): GamesRepository = HttpGamesRepository()
}
