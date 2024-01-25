import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import di.DI
import kotlinx.coroutines.MainScope

@OptIn(ExperimentalDecomposeApi::class)
fun MainViewController() = ComposeUIViewController {
    val root = remember {
        DI.rootComponent(
            componentContext = DefaultComponentContext(LifecycleRegistry()),
            mainContext = MainScope().coroutineContext
        )
    }

    App(component = root, modifier = Modifier.fillMaxSize())
}
