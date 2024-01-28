package dev.michalkonkel.gameshop

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun calculatePaddingValues(): AppPadding {
    val screenWidth = LocalWindowInfo.current.containerSize.width
    return if (screenWidth < 720) {
        AppPadding()
    } else {
        AppPadding(screenHorizontal = (screenWidth / 4).dp)
    }
}
