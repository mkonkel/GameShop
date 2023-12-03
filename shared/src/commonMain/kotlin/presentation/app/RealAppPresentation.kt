package presentation.app

import dev.michalkonkel.gameshop.domain.Game
import dev.michalkonkel.gameshop.repository.GamesRepository
import presentation.app.AppPresentation

internal class RealAppPresentation(
    private val repository: GamesRepository
) : AppPresentation {
    override suspend fun getGames(): List<Game> {
        return repository.getGames()
    }
}