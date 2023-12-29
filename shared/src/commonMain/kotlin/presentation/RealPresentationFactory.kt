package presentation

import presentation.app.AppPresentation
import presentation.app.RealAppPresentation
import repository.remote.RemoteRepositoryFactory

internal class RealPresentationFactory(
    private val remoteRepositoryFactory: RemoteRepositoryFactory,
) : PresentationFactory {
    override fun createAppPresentation(): AppPresentation =
        RealAppPresentation(
            loginRepository = remoteRepositoryFactory.loginRepository(),
            usersRepository = remoteRepositoryFactory.usersRepository(),
        )
}
