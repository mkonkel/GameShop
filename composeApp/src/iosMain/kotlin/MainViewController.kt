import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.ExperimentalDecomposeApi
import deeplink.DeepLink
import dev.michalkonkel.gameshop.App

@OptIn(ExperimentalDecomposeApi::class)
fun MainViewController() = ComposeUIViewController {
    App(
        modifier = Modifier.fillMaxSize(),
        deeplink = DeepLink.None,
        webHistoryController = null
    )
}