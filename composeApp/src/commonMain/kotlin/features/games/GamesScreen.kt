package features.games

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import features.logged.games.GamesComponent
import features.logged.games.GamesModel
import utils.observeModel

@Composable
internal fun GamesScreen(
    component: GamesComponent,
    modifier: Modifier = Modifier,
) {
    component.observeModel { model, _ ->
        Content(component, model, modifier)
    }
}

@Composable
private fun Content(
    component: GamesComponent,
    model: GamesModel,
    modifier: Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        model.games.forEach {
            Text(it)
        }
    }
}
