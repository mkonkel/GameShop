package di

import di.module.ApiModule
import di.module.StorageModule
import presentation.PresentationFactory
import presentation.RealPresentationFactory
import repository.remote.RealRemoteRepositoryFactory
import repository.remote.RemoteRepositoryFactory

object DI {
    private val storageModule: StorageModule by lazy { StorageModule() }
    private val apiModule: ApiModule by lazy { ApiModule(storageModule.tokenStorage) }

    private val remoteRepositoryFactory: RemoteRepositoryFactory by lazy {
        RealRemoteRepositoryFactory(apiModule.apiFactory)
    }

    val presentationFactory: PresentationFactory by lazy {
        RealPresentationFactory(
            gamesRepository = remoteRepositoryFactory.gamesRepository(),
        )
    }
}
