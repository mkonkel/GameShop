package dev.michalkonkel.gameshop.repository

import dev.michalkonkel.gameshop.domain.Game

interface GamesRepository {
    suspend fun getGames(): List<Game>
}