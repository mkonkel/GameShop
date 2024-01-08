@file:OptIn(ExperimentalDecomposeApi::class)

package di

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.webhistory.WebHistoryController
import deeplink.DeepLink
import di.module.ComponentModule
import di.module.RepositoryModule
import features.root.RootComponent

object DI {
    private val repositoryModule = RepositoryModule()
    private lateinit var componentModule: ComponentModule

    fun rootComponent(
        componentContext: ComponentContext,
        deepLink: DeepLink = DeepLink.None,
        webHistoryController: WebHistoryController? = null,
    ): RootComponent {
        return ComponentModule(componentContext, repositoryModule.remoteRepositoryFactory)
            .also { componentModule = it }
            .componentFactory.createRootComponent(deepLink, webHistoryController)
    }
}