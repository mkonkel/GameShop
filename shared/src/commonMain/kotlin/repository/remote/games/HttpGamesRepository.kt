package repository.remote.games

import dev.michalkonkel.gameshop.domain.games.AddGameRequest
import dev.michalkonkel.gameshop.domain.games.Game
import dev.michalkonkel.gameshop.repository.games.GamesRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

internal class HttpGamesRepository(private val client: HttpClient) : GamesRepository {
    override suspend fun getGames(): List<Game> {
        return client.get(GamesRepository.GAMES_ENDPOINT).body()
    }

    override suspend fun getGame(id: String): Game? {
        return client.get("${GamesRepository.GAMES_ENDPOINT}/$id").body()
    }

    override suspend fun addGame(game: AddGameRequest): Game {
        return client.post(GamesRepository.GAMES_ENDPOINT) { setBody(game) }.body()
    }
}
