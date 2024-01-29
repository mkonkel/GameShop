package features.widget.input

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import widget.input.AuthInput

@Composable
fun AuthInput.Widget(modifier: Modifier) {
    var currentText by remember(this) { mutableStateOf("Hello world!") }

    BasicTextField(
        value = currentText,
        onValueChange = { currentText = it },
        modifier = Modifier.then(modifier),
        singleLine = true,
        maxLines = 0,
        minLines = 0,
    )
}
