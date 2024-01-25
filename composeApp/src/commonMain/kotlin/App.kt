@file:OptIn(ExperimentalCoilApi::class)

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.memory.MemoryCache
import coil3.request.crossfade
import coil3.util.DebugLogger
import features.RootComponent
import features.RootScreen

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

    RootScreen(component = component, modifier = modifier)
}
