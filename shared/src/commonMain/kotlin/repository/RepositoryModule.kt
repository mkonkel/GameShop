package repository

import org.koin.dsl.module
import repository.local.localRepositoryModule
import repository.remote.remoteRepositoryModule

internal val repositoryModule =
    module {
        includes(localRepositoryModule, remoteRepositoryModule)
    }
