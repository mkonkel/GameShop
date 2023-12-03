package presentation.app

import dev.michalkonkel.gameshop.domain.Game

interface AppPresentation {
    suspend fun getGames(): List<Game>
}