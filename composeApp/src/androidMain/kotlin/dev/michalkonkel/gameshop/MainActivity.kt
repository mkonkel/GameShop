package dev.michalkonkel.gameshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import deeplink.DeepLink

@OptIn(ExperimentalDecomposeApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(
                modifier = Modifier.fillMaxSize(),
                deeplink = DeepLink.None,
                webHistoryController = null,
            )
        }
    }
}
