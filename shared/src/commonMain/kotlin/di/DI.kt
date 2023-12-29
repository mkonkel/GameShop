package di

import di.module.RepositoryModule
import presentation.PresentationFactory
import presentation.RealPresentationFactory

object DI {
    private val repositoryModule by lazy { RepositoryModule() }

    val presentationFactory: PresentationFactory by lazy {
        RealPresentationFactory(
            remoteRepositoryFactory = repositoryModule.remoteRepositoryFactory,
        )
    }
}
