package features.orders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import features.games.detail.OrdersComponent
import features.games.detail.OrdersModel
import utils.observeModel

@Composable
internal fun OrdersScreen(component: OrdersComponent) {
    component.observeModel { model ->
        Content(component, model)
    }
}

@Composable
private fun Content(
    component: OrdersComponent,
    model: OrdersModel,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        ) {
            model.items.forEach {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        modifier = Modifier.width(20.dp),
                        text = it.id,
                    )
                    Spacer(modifier = Modifier.requiredWidth(5.dp))
                    Column {
                        Text(text = it.date)
                        Spacer(modifier = Modifier.requiredHeight(5.dp))
                        Row {
                            Text(
                                text = "Quantity:",
                                fontWeight = FontWeight.Bold,
                            )
                            Text(text = it.price)
                        }
                    }
                    Text(
                        text = it.price,
                    )
                }
            }
        }
    }
}
