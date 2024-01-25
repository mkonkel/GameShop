package ui.widget.input

import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import widget.input.InputText

@Composable
fun InputText.Widget(
    modifier: Modifier,
    imageVector: ImageVector? = null,
) {
    var currentText by remember { text }

    OutlinedTextField(
        value = currentText,
        onValueChange = {
            currentText = it
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
