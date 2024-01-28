@file:OptIn(ExperimentalDecomposeApi::class)

package di

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.webhistory.WebHistoryController
import deeplink.DeepLink
import dev.michalkonkel.gameshop.domain.user.User
import features.RootComponent
import features.factory.RealComponentFactory
import repository.local.RealTokenStorage
import repository.local.TokenStorage
import repository.remote.RealRemoteRepository
import repository.remote.RemoteRepository
import repository.remote.client.HttpClientFactory
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalDecomposeApi::class)
object DI {
    private val tokenStorage: TokenStorage = RealTokenStorage()
    private val httpClientFactory: HttpClientFactory = HttpClientFactory(tokenStorage)
    private val remoteRepository: RemoteRepository = RealRemoteRepository(httpClientFactory.create(), tokenStorage)

    fun rootComponent(
        componentContext: ComponentContext,
        mainContext: CoroutineContext,
        deepLink: DeepLink = DeepLink.None,
        webHistoryController: WebHistoryController? = null,
    ): RootComponent {
        return RealComponentFactory(
            mainContext = mainContext,
            remoteRepository = remoteRepository,
        ).createRootComponent(
            deepLink = deepLink,
            webHistoryController = webHistoryController,
            componentContext = componentContext,
        )
    }

    internal lateinit var currentUser: User
}
