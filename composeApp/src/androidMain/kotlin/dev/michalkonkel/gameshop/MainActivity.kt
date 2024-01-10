package dev.michalkonkel.gameshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.retainedComponent
import di.DI
import features.RootScreen
import kotlinx.coroutines.MainScope

@OptIn(ExperimentalDecomposeApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = retainedComponent { DI.rootComponent(it, MainScope().coroutineContext) }

        setContent {
            RootScreen(component = root, modifier = Modifier.fillMaxSize())
        }
    }
}