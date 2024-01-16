package features.root.factory

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.webhistory.WebHistoryController
import deeplink.DeepLink
import features.root.RootComponent
import features.root.login.LoginComponent
import features.root.login.RegisterComponent

@OptIn(ExperimentalDecomposeApi::class)
interface RootComponentFactory {
    fun createRootComponent(
        deepLink: DeepLink,
        webHistoryController: WebHistoryController?,
        componentContext: ComponentContext,
    ): RootComponent

    fun createRegisterComponent(componentContext: ComponentContext): RegisterComponent

    fun createLoginComponent(componentContext: ComponentContext): LoginComponent
}
