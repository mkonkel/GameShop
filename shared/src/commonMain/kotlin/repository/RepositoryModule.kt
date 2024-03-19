package repository

import org.koin.dsl.module
import repository.local.storageModule
import repository.remote.remoteRepositoryModule

internal val repositoryModule =
    module {
        includes(storageModule, remoteRepositoryModule)
    }
