package features

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import features.home.HomeScreen
import features.login.LoginScreen
import features.root.RootComponent

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
                    modifier = Modifier.fillMaxSize(),
                )

            is RootComponent.Child.RegisterChild -> Text("REGISTER")
            is RootComponent.Child.HomeChild ->
                HomeScreen(
                    child.component,
                    modifier = Modifier.fillMaxSize(),
                )
        }
    }
}
