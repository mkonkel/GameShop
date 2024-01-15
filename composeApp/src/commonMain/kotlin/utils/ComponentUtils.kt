package utils

import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import features.Component
import features.utils.Model
import features.utils.ModelState

@Composable
fun <T : Model> Component<T>.observeModel(
    loading: @Composable (Modifier) -> Unit = { DefaultProgressIndicator(it) },
    error: @Composable (String) -> Unit = { Text("Error: $it") },
    content: @Composable (T, Modifier) -> Unit,
) {
    when (val modelState = this.modelState.collectAsState().value) {
        is ModelState.Loading -> loading(Modifier)
        is ModelState.Success -> content(modelState.model, Modifier)
        is ModelState.Error -> error(modelState.message)
    }
}

@Composable
private fun DefaultProgressIndicator(modifier: Modifier) {
    CircularProgressIndicator(modifier = modifier.width(64.dp))
}
