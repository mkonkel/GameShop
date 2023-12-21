package repository.remote.games

import dev.michalkonkel.gameshop.domain.games.Game
import dev.michalkonkel.gameshop.domain.games.GameRequest
import dev.michalkonkel.gameshop.repository.GamesRepository

internal class HttpGamesRepository : GamesRepository {
    override suspend fun getGames(): List<Game> {
        TODO("Not yet implemented")
    }

    override suspend fun getGame(id: String): Game? {
        TODO("Not yet implemented")
    }

    override suspend fun addGame(game: GameRequest): Game? {
        TODO("Not yet implemented")
    }
}
