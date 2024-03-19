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
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.webhistory.WebHistoryController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import deeplink.DeepLink
import dev.michalkonkel.gameshop.features.RootScreen
import features.RootComponent
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf
import sharedModule

@OptIn(ExperimentalCoilApi::class, ExperimentalDecomposeApi::class)
@Composable
fun App(
    modifier: Modifier,
    deeplink: DeepLink = DeepLink.None,
    webHistoryController: WebHistoryController? = null,
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

    KoinApplication(application = { modules(sharedModule) }) {
        val rootComponent =
            koinInject<RootComponent> {
                parametersOf(
                    deeplink,
                    webHistoryController,
                    DefaultComponentContext(lifecycle = LifecycleRegistry()),
                )
            }

        CompositionLocalProvider(LocalAppPadding provides calculatePaddingValues()) {
            RootScreen(component = rootComponent, modifier = modifier)
        }
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
