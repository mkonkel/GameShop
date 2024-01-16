package features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import features.games.GamesScreen
import features.logged.LoggedComponent
import features.users.UsersScreen
import utils.observeModel

@Composable
internal fun HomeScreen(
    component: LoggedComponent,
    modifier: Modifier = Modifier,
) {
    component.observeModel { model, _ ->
        Scaffold(
            topBar = {
                Row(
                    modifier = Modifier.fillMaxWidth().background(Color.Gray),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Text(model.title)
                }
            },
            bottomBar = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Button(
                        onClick = { component.onUsersClick() },
                    ) {
                        Text("USERS")
                    }
                    Button(
                        onClick = { component.onGamesClick() },
                    ) {
                        Text("GAMES")
                    }
                }
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
            is LoggedComponent.Child.GamesChild -> GamesScreen(child.component, modifier)
            is LoggedComponent.Child.UsersChild -> UsersScreen(child.component, modifier)
        }
    }
}
