package dev.michalkonkel.gameshop.repository.games

import dev.michalkonkel.gameshop.domain.games.AddGameRequest
import dev.michalkonkel.gameshop.domain.games.Game

interface GamesRepository {
    suspend fun getGames(): List<Game>

    suspend fun getGame(id: String): Game?

    suspend fun addGame(game: AddGameRequest): Game

    companion object {
        const val GAMES_ENDPOINT = "/games"
    }
}
