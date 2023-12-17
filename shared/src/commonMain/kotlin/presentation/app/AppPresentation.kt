package presentation.app

import dev.michalkonkel.gameshop.domain.games.Game

interface AppPresentation {
    suspend fun getGames(): List<Game>
}
