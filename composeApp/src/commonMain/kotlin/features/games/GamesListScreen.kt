package features.games

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import features.logged.games.list.GamesListComponent
import features.logged.games.list.GamesListModel
import utils.observeModel

@Composable
internal fun GamesScreen(
    component: GamesListComponent,
    modifier: Modifier = Modifier,
) {
    component.observeModel { model, _ ->
        Content(component, model, modifier)
    }
}

@Composable
private fun Content(
    component: GamesListComponent,
    model: GamesListModel,
    modifier: Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        model.games.forEach {
            Button(
                onClick = { component.onGameClicked(it) },
            ) {
                Text(it)
            }
        }
    }
}
