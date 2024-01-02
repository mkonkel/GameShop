package dev.michalkonkel.gameshop.features.games.data

import dev.michalkonkel.gameshop.database.dbQuery
import dev.michalkonkel.gameshop.database.tables.GamesEntity
import dev.michalkonkel.gameshop.domain.games.Game
import dev.michalkonkel.gameshop.domain.games.GameRequest
import java.util.UUID

class RealGamesDAOFacade : GamesDAOFacade {
    override suspend fun createGame(gameRequest: GameRequest) = dbQuery {
        GamesEntity
            .new {
                name = gameRequest.name
                description = gameRequest.description
                price = gameRequest.price
            }
            .toGame()
    }

    override suspend fun getGame(id: String): Game? = dbQuery {
        GamesEntity
            .findById(UUID.fromString(id))
            ?.toGame()
    }

    override suspend fun getGames(): List<Game> =
        dbQuery {
            GamesEntity.all().map { it.toGame() }
        }

    private fun GamesEntity.toGame(): Game =
        this.let {
            Game(
                id = it.id.value.toString(),
                name = it.name,
                description = it.description,
                price = it.price,
            )
        }
}
