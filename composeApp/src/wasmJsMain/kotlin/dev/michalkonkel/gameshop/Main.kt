package dev.michalkonkel.gameshop

import App
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.configureWebResources
import org.jetbrains.compose.resources.urlResource

@OptIn(ExperimentalComposeUiApi::class, ExperimentalResourceApi::class)
fun main() {
    configureWebResources {
        // same as default - this is not necessary to add here. It's here to show this feature
        setResourceFactory { urlResource("./$it") }
    }

    CanvasBasedWindow(title = "GameShop", canvasElementId = "gameShopCanvas") {
        App()
    }
}