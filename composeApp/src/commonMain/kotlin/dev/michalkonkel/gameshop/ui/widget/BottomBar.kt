package dev.michalkonkel.gameshop.ui.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import widget.bottombar.BottomBarModel

@Composable
fun BottomBarModel.Widget() {
    BottomAppBar(
        modifier = Modifier.height(56.dp),
        actions = {
            items.forEach { it.Widget(Modifier.fillMaxWidth().weight(1f)) }
        },
    )
}
