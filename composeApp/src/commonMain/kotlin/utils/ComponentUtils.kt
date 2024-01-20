package utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import features.Component
import features.utils.Model
import features.utils.ModelState

@Composable
fun <T : Model> Component<T>.observeModel(
    loading: @Composable (Modifier) -> Unit = { DefaultProgressIndicator(it) },
    error: @Composable (String) -> Unit = { Text("Error: $it") },
    content: @Composable (T, Modifier) -> Unit,
) {
    when (val modelState = this.modelValue.subscribeAsState().value) {
        is ModelState.Loading -> loading(Modifier)
        is ModelState.Success -> content(modelState.model, Modifier)
        is ModelState.Error -> error(modelState.message)
    }
}

@Composable
private fun DefaultProgressIndicator(modifier: Modifier) {
    // https://github.com/android/sunflower/issues/942 CircularProgress crashes the App
//    CircularProgressIndicator(modifier = modifier.width(50.dp))
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Loading...")
    }
}
