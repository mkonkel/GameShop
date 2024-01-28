package dev.michalkonkel.gameshop.features.games

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.michalkonkel.gameshop.ui.widget.Widget
import dev.michalkonkel.gameshop.ui.widget.input.Widget
import dev.michalkonkel.gameshop.utils.observeModel
import features.games.add.AddGameComponent
import features.games.add.AddGameModel

@Composable
internal fun AddGameScreen(
    component: AddGameComponent,
    modifier: Modifier = Modifier,
) {
    component.observeModel { model ->
        Content(component, model, modifier)
    }
}

@Composable
private fun Content(
    component: AddGameComponent,
    model: AddGameModel,
    modifier: Modifier,
) {
    Scaffold(
        topBar = {
            model.topBar.Widget()
        },
        bottomBar = {
            model.addButton.Widget(Modifier.fillMaxWidth().padding(8.dp))
        },
    ) {
        with(model.content) {
            Column(
                modifier = modifier.verticalScroll(rememberScrollState()).padding(it),
                horizontalAlignment = Alignment.Start,
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                name.Widget(Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(16.dp))
                description.Widget(Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(16.dp))
                price.Widget(Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(16.dp))
                image.Widget(Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
