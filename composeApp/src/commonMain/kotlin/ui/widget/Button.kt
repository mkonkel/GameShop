package ui.widget

import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import widget.button.Button.Style.FILLED
import widget.button.Button.Style.FLOATING
import widget.button.Button.Style.OUTLINED
import widget.button.Button.Style.TEXT
import widget.button.Button as AppButton

@Composable
fun AppButton.Widget(modifier: Modifier = Modifier) {
    when (this.style) {
        FILLED -> {
            Button(modifier = modifier, onClick = this@Widget.onClick) {
                Text(text = this@Widget.text)
            }
        }

        OUTLINED -> {
            OutlinedButton(modifier = modifier, onClick = this@Widget.onClick) {
                Text(text = this@Widget.text)
            }
        }

        TEXT -> {
            TextButton(modifier = modifier, onClick = this@Widget.onClick) {
                Text(text = this@Widget.text)
            }
        }

        FLOATING ->
            ExtendedFloatingActionButton(
                modifier = modifier,
                onClick = this@Widget.onClick,
            ) {
                Text(text = this@Widget.text)
            }
    }
}
