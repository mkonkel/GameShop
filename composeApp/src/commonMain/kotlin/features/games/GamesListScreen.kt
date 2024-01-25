package features.games

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import features.games.list.GamesListComponent
import features.games.list.GamesListModel
import ui.widget.game.Widget
import utils.observeModel

@Composable
internal fun GamesScreen(component: GamesListComponent) {
    component.observeModel { model ->
        Content(component, model)
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
