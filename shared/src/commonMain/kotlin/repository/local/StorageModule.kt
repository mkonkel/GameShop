package repository.local

import org.koin.dsl.module

internal val storageModule =
    module {
        single<TokenStorage> { RealTokenStorage() }
    }
