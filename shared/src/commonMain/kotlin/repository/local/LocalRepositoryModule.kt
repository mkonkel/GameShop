package repository.local

import org.koin.dsl.module
import repository.local.token.RealTokenStorage
import repository.local.token.TokenStorage
import repository.local.user.UserHolder

internal val localRepositoryModule =
    module {
        single<TokenStorage> { RealTokenStorage() }
        single { UserHolder() }
    }
