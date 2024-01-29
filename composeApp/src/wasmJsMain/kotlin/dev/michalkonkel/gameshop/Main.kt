package dev.michalkonkel.gameshop

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.CanvasBasedWindow
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.webhistory.DefaultWebHistoryController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import deeplink.DeepLink
import di.DI
import features.RootScreen
import kotlinx.browser.window
import kotlinx.coroutines.MainScope

@OptIn(ExperimentalComposeUiApi::class, ExperimentalDecomposeApi::class)
fun main() {
    val root =
        DI.rootComponent(
            componentContext = DefaultComponentContext(lifecycle = LifecycleRegistry()),
            deepLink = DeepLink.Web(path = window.location.pathname),
            webHistoryController = DefaultWebHistoryController(),
            mainContext = MainScope().coroutineContext,
        )

    CanvasBasedWindow(title = "GameShop", canvasElementId = "gameShopCanvas") {
        RootScreen(component = root, modifier = Modifier.fillMaxSize())
    }
}
