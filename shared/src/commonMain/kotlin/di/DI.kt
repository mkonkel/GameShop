package di

import presentation.PresentationFactory
import presentation.RealPresentationFactory
import repository.RealRepositoryFactory
import repository.RepositoryFactory

object DI {
    private val repositoryFactory: RepositoryFactory by lazy {
        RealRepositoryFactory()
    }

    val presentationFactory: PresentationFactory by lazy {
        RealPresentationFactory(
            gamesRepository = repositoryFactory.gamesRepository(),
        )
    }
}
