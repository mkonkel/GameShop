package dev.michalkonkel.gameshop.features.games.domain

import dev.michalkonkel.gameshop.domain.Game
import dev.michalkonkel.gameshop.repository.GamesRepository

class DatabaseGamesRepository : GamesRepository {
    override suspend fun getGames(): List<Game> {
        return listOf(
            Game(name = "Game from Database Repository")
        )
    }
}