package dev.michalkonkel.gameshop.features.orders

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.michalkonkel.gameshop.ui.widget.order.Widget
import dev.michalkonkel.gameshop.utils.observeModel
import features.games.detail.OrdersComponent
import features.games.detail.OrdersModel

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
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
    ) {
        model.items.forEach { order ->
            order.Widget { }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
