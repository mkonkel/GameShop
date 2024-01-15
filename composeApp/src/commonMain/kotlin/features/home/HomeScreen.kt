package features.home

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import features.logged.LoggedComponent
import utils.Component.observeModel

@Composable
internal fun HomeScreen(
    component: LoggedComponent,
    modifier: Modifier = Modifier,
) {
    component.observeModel { model, _ ->
        Scaffold(
            topBar = {
                Text(model.title)
            },
            bottomBar = {
                Text("Bottom bar")
            },
        ) {
            Children(component, modifier)
        }
    }
}

@Composable
private fun Children(
    component: LoggedComponent,
    modifier: Modifier = Modifier,
) {
    Children(
        stack = component.childStack,
        modifier = modifier,
        animation = stackAnimation(fade()),
    ) {
        when (val child = it.instance) {
            is LoggedComponent.Child.GamesChild -> Text("GAMES")
            is LoggedComponent.Child.UsersChild -> Text("USERS")
        }
    }
}
