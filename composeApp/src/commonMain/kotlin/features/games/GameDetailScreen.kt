package features.games

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import features.logged.games.detail.GameDetailsComponent
import features.logged.games.detail.GameDetailsModel
import utils.observeModel

@Composable
internal fun GameDetailsScreen(
    component: GameDetailsComponent,
    modifier: Modifier = Modifier,
) {
    component.observeModel { model, _ ->
        Content(component, model, modifier)
    }
}

@Composable
private fun Content(
    component: GameDetailsComponent,
    model: GameDetailsModel,
    modifier: Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(model.name)
            Spacer(modifier = Modifier.requiredHeight(20.dp))
            Text(model.description)
            Spacer(modifier = Modifier.requiredHeight(20.dp))
            Text("Rating: ${model.rating}/5")
        }
    }
}
