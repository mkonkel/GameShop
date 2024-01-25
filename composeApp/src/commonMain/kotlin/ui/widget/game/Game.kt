package ui.widget.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import features.games.list.GamesListModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamesListModel.Item.Widget(onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model =
                    ImageRequest.Builder(LocalPlatformContext.current)
                        .data(image)
                        .build(),
                contentDescription = null,
                placeholder = ColorPainter(Color(0xFF8B8682)),
                error = ColorPainter(Color.Red),
                contentScale = ContentScale.Fit,
                modifier =
                    Modifier
                        .height(100.dp)
                        .width(80.dp),
            )
            Spacer(modifier = Modifier.requiredWidth(8.dp))
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = description,
                    fontWeight = FontWeight.Light,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Spacer(modifier = Modifier.requiredWidth(8.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Rating",
                    fontWeight = FontWeight.Bold,
                )
                Text(text = rating.toString())
            }
        }
    }
}
