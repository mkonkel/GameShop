package ui.widget.input

import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import widget.input.InputText

@Composable
fun InputText.Widget(
    modifier: Modifier,
    imageVector: ImageVector? = null,
) {
    val currentText by text.subscribeAsState()

    OutlinedTextField(
        value = currentText,
        onValueChange = {
            println("onValueChange: $it")
            updateText(it)
        },
        modifier = Modifier.then(modifier),
        leadingIcon = {
            imageVector?.let {
                Icon(imageVector = imageVector, contentDescription = "")
            }
        },
        label = {
            println("label: $label")
            Text(label)
        },
        singleLine = maxLines == 1,
        maxLines = maxLines,
        visualTransformation =
            if (type == InputText.Type.SECURE) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
    )
}
