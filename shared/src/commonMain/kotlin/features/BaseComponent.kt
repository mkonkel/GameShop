package features

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.doOnDestroy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

internal abstract class BaseComponent(
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext,
) : ComponentContext by componentContext {
    protected val scope by lazy { coroutineScope(coroutineContext + SupervisorJob()) }
//    protected val scope by lazy { coroutineScope(Dispatchers.Default + SupervisorJob()) }

    private fun CoroutineScope(
        context: CoroutineContext,
        lifecycle: Lifecycle,
    ): CoroutineScope {
        val scope = CoroutineScope(context)
        lifecycle.doOnDestroy { scope.coroutineContext.cancelChildren() }
        return scope
    }

    private fun LifecycleOwner.coroutineScope(context: CoroutineContext): CoroutineScope = CoroutineScope(context, lifecycle)
//    private fun LifecycleOwner.coroutineScope(context: CoroutineContext): CoroutineScope = CoroutineScope(context + Dispatchers.Default, lifecycle)
}
