package dev.michalkonkel.gameshop.features.games

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import dev.michalkonkel.gameshop.ui.widget.Widget
import dev.michalkonkel.gameshop.utils.observeModel
import features.games.detail.GameDetailsComponent
import features.games.detail.GameDetailsModel

@Composable
internal fun GameDetailsScreen(
    component: GameDetailsComponent,
    modifier: Modifier = Modifier,
) {
    component.observeModel { model ->
        Content(component, model, modifier)
    }
}

@Composable
private fun Content(
    component: GameDetailsComponent,
    model: GameDetailsModel,
    modifier: Modifier,
) {
    Scaffold(
        topBar = {
            model.topBar.Widget()
        },
        bottomBar = {
            model.addToCardButton.Widget(Modifier.fillMaxWidth().padding(8.dp))
        },
    ) {
        with(model.content) {
            Column(
                modifier = modifier.verticalScroll(rememberScrollState()).padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Spacer(modifier = Modifier.requiredHeight(8.dp))
                AsyncImage(
                    model =
                        ImageRequest.Builder(LocalPlatformContext.current)
                            .data(image)
                            .build(),
                    contentDescription = null,
                    placeholder = ColorPainter(Color(0xFF8B8682)),
                    error = ColorPainter(Color.Red),
                    contentScale = ContentScale.FillHeight,
                    modifier =
                        Modifier
                            .height(360.dp)
                            .width(280.dp),
                )
                Spacer(modifier = Modifier.requiredHeight(8.dp))
                Row {
                    Text(
                        text = "Price:",
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = price,
                    )
                }
                Spacer(modifier = Modifier.requiredHeight(8.dp))
                Text(text = description)
                Spacer(modifier = Modifier.requiredHeight(8.dp))
            }
        }
    }
}
