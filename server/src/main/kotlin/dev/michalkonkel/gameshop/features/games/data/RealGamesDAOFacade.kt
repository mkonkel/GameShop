package dev.michalkonkel.gameshop.features.games.data

import dev.michalkonkel.gameshop.database.dbQuery
import dev.michalkonkel.gameshop.database.tables.Games
import dev.michalkonkel.gameshop.domain.games.Game
import dev.michalkonkel.gameshop.domain.games.GameRequest
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import java.util.UUID

class RealGamesDAOFacade : GamesDAOFacade {
    override suspend fun createGame(gameRequest: GameRequest) =
        dbQuery {
            Games
                .insert {
                    it[name] = gameRequest.name
                    it[description] = gameRequest.description
                    it[price] = gameRequest.price
                }.resultedValues
                ?.singleOrNull()
                ?.toGame()
        }

    override suspend fun getGame(id: String): Game? =
        dbQuery {
            Games
                .select { Games.id eq UUID.fromString(id) }
                .limit(1)
                .firstOrNull()
                ?.toGame()
        }

    override suspend fun getGames(): List<Game> =
        dbQuery {
            Games.selectAll().map { it.toGame() }
        }

    private fun ResultRow.toGame(): Game =
        this.let {
            Game(
                id = it[Games.id].toString(),
                name = it[Games.name],
                description = it[Games.description],
                price = it[Games.price],
            )
        }
}
