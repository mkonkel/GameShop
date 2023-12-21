package di.module

import repository.local.RealTokenStorage
import repository.local.TokenStorage

internal class StorageModule {
    val tokenStorage: TokenStorage by lazy { RealTokenStorage() }
}