package presentation.app

import dev.michalkonkel.gameshop.domain.games.Game
import dev.michalkonkel.gameshop.repository.GamesRepository

internal class RealAppPresentation(
    private val repository: GamesRepository
) : AppPresentation {
    override suspend fun getGames(): List<Game> {
        return repository.getGames()
    }
}