@file:OptIn(ExperimentalCoilApi::class)

package dev.michalkonkel.gameshop

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.memory.MemoryCache
import coil3.request.crossfade
import coil3.util.DebugLogger
import dev.michalkonkel.gameshop.features.RootScreen
import features.RootComponent

@Composable
fun App(
    component: RootComponent,
    modifier: Modifier,
) {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .memoryCache {
                MemoryCache.Builder()
                    .maxSizePercent(context, percent = 0.25)
                    .build()
            }
            .crossfade(true)
            .apply {
                logger(DebugLogger())
            }
            .build()
    }

    CompositionLocalProvider(
        LocalAppPadding provides calculatePaddingValues(),
    ) {
        RootScreen(component = component, modifier = modifier)
    }
}

private val LocalAppPadding = staticCompositionLocalOf { AppPadding() }

data class AppPadding(
    val screenHorizontal: Dp = 20.dp,
    val screenVertical: Dp = 10.dp,
)

val Padding
    @ReadOnlyComposable
    @Composable
    get() = LocalAppPadding.current
