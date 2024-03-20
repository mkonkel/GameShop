package dev.michalkonkel.gameshop

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.CanvasBasedWindow
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.webhistory.DefaultWebHistoryController
import deeplink.DeepLink
import kotlinx.browser.window

@OptIn(ExperimentalComposeUiApi::class, ExperimentalDecomposeApi::class)
fun main() {
    CanvasBasedWindow(title = "GameShop", canvasElementId = "gameShopCanvas") {
        App(
            modifier = Modifier.fillMaxSize(),
            deeplink = DeepLink.Web(path = window.location.pathname),
            webHistoryController = DefaultWebHistoryController(),
        )
    }
}
