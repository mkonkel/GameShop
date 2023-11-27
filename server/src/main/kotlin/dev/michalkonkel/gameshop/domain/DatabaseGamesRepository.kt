package dev.michalkonkel.gameshop.domain

import dev.michalkonkel.gameshop.repository.GamesRepository

class DatabaseGamesRepository : GamesRepository {
    override suspend fun getGames(): List<Game> {
        TODO("Not yet implemented - should call the DB")
    }
}