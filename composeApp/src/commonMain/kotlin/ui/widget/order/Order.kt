package ui.widget.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import features.games.detail.OrdersModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersModel.Item.Widget(onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.requiredWidth(8.dp))
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Row {
                    Text(
                        text = "Order id:",
                        fontWeight = FontWeight.Bold,
                    )
                    Text(text = id)
                }
                Row {
                    Text(
                        text = "Address:",
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = address,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                Row {
                    Text(
                        text = "Date:",
                        fontWeight = FontWeight.Bold,
                    )
                    Text(text = orderDate)
                }
                Row {
                    Text(
                        text = "Games:",
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = games.joinToString(", "),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            Spacer(modifier = Modifier.requiredWidth(8.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Price:",
                    fontWeight = FontWeight.Bold,
                )
                Text(text = price)
            }
        }
    }
}
