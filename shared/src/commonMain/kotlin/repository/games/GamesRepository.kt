package repository.games

import dev.michalkonkel.gameshop.domain.Game
import dev.michalkonkel.gameshop.repository.GamesRepository

internal class HttpGamesRepository : GamesRepository {
    override suspend fun getGames(): List<Game> {
        return listOf(
            Game(name = "Doom"),
            Game(name = "Quake"),
            Game(name = "Duke Nukem 3D"),
            Game(name = "System Shock"),
            Game(name = "Wolfenstein 3D"),
            Game(name = "Half Life")
        )
    }
}