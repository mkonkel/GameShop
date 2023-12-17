package dev.michalkonkel.gameshop

import App
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow(title = "GameShop", canvasElementId = "gameShopCanvas") {
        App()
    }
}
