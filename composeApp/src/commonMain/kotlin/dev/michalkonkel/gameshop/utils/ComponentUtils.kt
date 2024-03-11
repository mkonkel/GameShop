package dev.michalkonkel.gameshop.utils

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import features.Component
import features.utils.Model
import features.utils.ModelState
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun <T : Model> Component<T>.observeModel(
    loading: @Composable () -> Unit = { DefaultProgressIndicator() },
    error: @Composable (String) -> Unit = { Text("Error: $it") },
    content: @Composable (T) -> Unit,
) {
    when (val modelState = this.modelValue.subscribeAsState().value) {
        is ModelState.Loading -> loading()
        is ModelState.Success -> content(modelState.model)
        is ModelState.Error -> error(modelState.message)
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun DefaultProgressIndicator() {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LoadingAnimation()
    }
}

@ExperimentalResourceApi
@Composable
private fun LoadingAnimation() {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 100f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(tween(1000)),
    )

    Image(
        painter = painterResource(DrawableResource("space-dogfighter-logo.png")),
        modifier = Modifier.size(100.dp).offset(y = rotation.dp),
        contentDescription = "space-dogfighter-logo",
    )
    Spacer(Modifier.height(100.dp))
    Text(
        text = "Loading...",
        fontWeight = FontWeight.Black,
        fontSize = 20.sp,
        color = Color(0xFFFF9966),
    )
}
