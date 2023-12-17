import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
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
import dev.michalkonkel.gameshop.domain.games.Game
import di.DI
import kotlinx.coroutines.launch

@Composable
fun App() {
    MaterialTheme {
        Content()
    }
}

@Composable
private fun Content() {
    val presentation = remember { DI.presentationFactory.createAppPresentation() }
    var gamesState by remember { mutableStateOf<List<Game>?>(null) }
    val scope = rememberCoroutineScope()

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Compose: ${Greeting().greet()}")

        Button(onClick = {
            scope.launch {
                gamesState = presentation.getGames()
            }
        }) {
            Text("Fetch games")
        }

        Games(gamesState)
    }
}

@Composable
private fun Games(games: List<Game>?) {
    if (games == null) return

    Column(
        modifier = Modifier.fillMaxWidth().padding(20.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            text = "Games:",
        )

        Spacer(Modifier.height(20.dp))

        games.forEach { game ->
            Text(game.name)
        }
    }
}
