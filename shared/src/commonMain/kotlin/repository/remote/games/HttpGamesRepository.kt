package repository.remote.games

import dev.michalkonkel.gameshop.domain.games.Game
import dev.michalkonkel.gameshop.domain.games.AddGameRequest
import dev.michalkonkel.gameshop.repository.games.GamesRepository
import io.ktor.client.HttpClient

internal class HttpGamesRepository(private val client: HttpClient) : GamesRepository {
    override suspend fun getGames(): List<Game> {
        TODO("Not yet implemented")
    }

    override suspend fun getGame(id: String): Game? {
        TODO("Not yet implemented")
    }

    override suspend fun addGame(game: AddGameRequest): Game {
        TODO("Not yet implemented")
    }
}
