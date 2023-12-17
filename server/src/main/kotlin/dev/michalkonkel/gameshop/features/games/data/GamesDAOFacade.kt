package dev.michalkonkel.gameshop.features.games.data

import dev.michalkonkel.gameshop.domain.games.Game
import dev.michalkonkel.gameshop.domain.games.GameRequest

interface GamesDAOFacade {
    suspend fun createGame(gameRequest: GameRequest): Game?

    suspend fun getGame(id: String): Game?

    suspend fun getGames(): List<Game>
}
