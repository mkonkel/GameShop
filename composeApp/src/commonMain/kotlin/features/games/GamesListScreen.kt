package features.games

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import features.games.list.GamesListComponent
import features.games.list.GamesListModel
import ui.widget.game.Widget
import utils.observeModel

@Composable
internal fun GamesScreen(component: GamesListComponent) {
    component.observeModel { model ->
        Box {
            Content(component, model)
            ExtendedFloatingActionButton(
                modifier = Modifier.align(Alignment.BottomEnd).padding(bottom = 16.dp),
                onClick = { component.onAddClicked() },
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
                Text(text = "Add Game")
            }
        }
    }
}

@Composable
private fun Content(
    component: GamesListComponent,
    model: GamesListModel,
) {
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
    ) {
        model.games.forEach { game ->
            game.Widget { component.onGameClicked(game.id) }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
