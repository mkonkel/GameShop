package dev.michalkonkel.gameshop.features.games

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.michalkonkel.gameshop.ui.widget.Widget
import dev.michalkonkel.gameshop.ui.widget.game.Widget
import dev.michalkonkel.gameshop.utils.observeModel
import features.games.list.GamesListComponent
import features.games.list.GamesListModel

@Composable
internal fun GamesScreen(component: GamesListComponent) {
    component.observeModel { model ->
        Box {
            Content(component, model)
            model.addButton?.Widget(Modifier.align(Alignment.BottomEnd).padding(bottom = 16.dp))
        }
    }
}

@Composable
private fun Content(
    component: GamesListComponent,
    model: GamesListModel,
) {
    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        model.games.forEach { game ->
            game.Widget { component.onGameClicked(game.id) }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
