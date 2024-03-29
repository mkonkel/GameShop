package dev.michalkonkel.gameshop.features

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import dev.michalkonkel.gameshop.Padding
import dev.michalkonkel.gameshop.features.games.AddGameScreen
import dev.michalkonkel.gameshop.features.games.GameDetailsScreen
import dev.michalkonkel.gameshop.features.home.HomeScreen
import dev.michalkonkel.gameshop.features.login.LoginScreen
import dev.michalkonkel.gameshop.features.register.RegisterScreen
import features.RootComponent

@Composable
fun RootScreen(
    component: RootComponent,
    modifier: Modifier = Modifier,
) {
    MaterialTheme {
        Surface(modifier = modifier, color = MaterialTheme.colorScheme.background) {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Top + WindowInsetsSides.Horizontal)),
            ) {
                Children(component = component)
            }
        }
    }
}

@Composable
private fun Children(
    component: RootComponent,
    modifier: Modifier = Modifier,
) {
    Children(
        stack = component.childStack,
        modifier = modifier,
        animation = stackAnimation(fade()),
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.LoginChild ->
                LoginScreen(
                    child.component,
                    modifier =
                        Modifier
                            .padding(horizontal = Padding.screenHorizontal)
                            .fillMaxSize(),
                )

            is RootComponent.Child.RegisterChild ->
                RegisterScreen(
                    child.component,
                    modifier = Modifier.padding(horizontal = 16.dp).fillMaxSize(),
                )

            is RootComponent.Child.HomeChild ->
                HomeScreen(
                    child.component,
                    modifier = Modifier.padding(horizontal = 16.dp).fillMaxSize(),
                )

            is RootComponent.Child.GameDetails ->
                GameDetailsScreen(
                    child.component,
                    modifier = Modifier.padding(horizontal = 16.dp).fillMaxSize(),
                )

            is RootComponent.Child.AddGame ->
                AddGameScreen(
                    child.component,
                    modifier = Modifier.padding(horizontal = 16.dp).fillMaxSize(),
                )
        }
    }
}
