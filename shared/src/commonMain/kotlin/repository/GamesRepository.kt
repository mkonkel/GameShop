package repository

import dev.michalkonkel.gameshop.domain.Game
import dev.michalkonkel.gameshop.repository.GamesRepository

class HttpGamesRepository : GamesRepository {
    override suspend fun getGames(): List<Game> {
        TODO("Not yet implemented - KTOR client call")
    }
}