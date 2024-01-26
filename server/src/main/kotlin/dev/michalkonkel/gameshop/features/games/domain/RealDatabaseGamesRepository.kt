package dev.michalkonkel.gameshop.features.games.domain

import dev.michalkonkel.gameshop.domain.games.AddGameRequest
import dev.michalkonkel.gameshop.domain.games.Game
import dev.michalkonkel.gameshop.features.games.data.GamesDAOFacade
import dev.michalkonkel.gameshop.repository.games.GamesRepository

internal class RealDatabaseGamesRepository(
    private val dao: GamesDAOFacade,
) : GamesRepository {
    override suspend fun getGames(): List<Game> = dao.getGames()

    override suspend fun getGame(id: String): Game? = dao.getGame(id)

    override suspend fun addGame(game: AddGameRequest): Game = dao.createGame(game)
}
