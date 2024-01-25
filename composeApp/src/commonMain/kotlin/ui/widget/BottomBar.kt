package ui.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import widget.bottombar.BottomBarModel

@Composable
fun BottomBarModel.Widget() {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(50.dp),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        items.forEach {
            it.Widget()
        }
    }
}

@Composable
private fun BottomBarModel.Item.Widget() {
    Box(
        modifier =
            Modifier
                .height(50.dp)
                .clickable { onClick(this) },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = label,
            fontWeight = if (isSelected.value) FontWeight.Bold else null,
        )
    }
}
