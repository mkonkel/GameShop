package presentation

import dev.michalkonkel.gameshop.repository.GamesRepository
import presentation.app.AppPresentation
import presentation.app.RealAppPresentation

internal class RealPresentationFactory(
    private val gamesRepository: GamesRepository,
) : PresentationFactory {
    override fun createAppPresentation(): AppPresentation = RealAppPresentation(repository = gamesRepository)
}
