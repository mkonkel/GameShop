package features.login

import Greeting
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.michalkonkel.gameshop.domain.user.User
import di.DI
import kotlinx.coroutines.launch

@Composable
internal fun LoginScreen(component: LoginComponent, modifier: Modifier = Modifier) {
    Content(component, modifier)
}

@Composable
private fun Content(component: LoginComponent, modifier: Modifier) {
    val presentation = remember { DI.presentationFactory.createAppPresentation() }
    var usersState by remember { mutableStateOf<List<User>?>(null) }
    val scope = rememberCoroutineScope()

    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Compose: ${Greeting().greet()}")
        Text(component.model.title)

        Button(onClick = {
            scope.launch {
                usersState = presentation.getUsers()
            }
        }) {
            Text("Fetch Users")
        }

        Users(usersState)
    }
}

@Composable
private fun Users(games: List<User>?) {
    if (games == null) return

    Column(
        modifier = Modifier.fillMaxWidth().padding(20.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            text = "Users:",
        )

        Spacer(Modifier.height(20.dp))

        games.forEach { game ->
            Text(game.name)
        }
    }
}