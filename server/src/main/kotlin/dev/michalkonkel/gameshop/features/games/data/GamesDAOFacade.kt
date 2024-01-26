package dev.michalkonkel.gameshop.features.games.data

import dev.michalkonkel.gameshop.domain.games.Game
import dev.michalkonkel.gameshop.domain.games.AddGameRequest

interface GamesDAOFacade {
    suspend fun createGame(addGameRequest: AddGameRequest): Game

    suspend fun getGame(id: String): Game?

    suspend fun getGames(): List<Game>
}
