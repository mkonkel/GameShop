package dev.michalkonkel.gameshop.features.games.data

import dev.michalkonkel.gameshop.database.dbQuery
import dev.michalkonkel.gameshop.database.tables.GamesEntity
import dev.michalkonkel.gameshop.domain.games.Game
import dev.michalkonkel.gameshop.domain.games.AddGameRequest
import java.util.UUID

class RealGamesDAOFacade : GamesDAOFacade {
    override suspend fun createGame(addGameRequest: AddGameRequest) =
        dbQuery {
            GamesEntity
                .new {
                    name = addGameRequest.name
                    description = addGameRequest.description
                    price = addGameRequest.price
                    imageUrl = addGameRequest.imageUrl
                }
                .toGame()
        }

    override suspend fun getGame(id: String): Game? =
        dbQuery {
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
                imageUrl = it.imageUrl
            )
        }
}
