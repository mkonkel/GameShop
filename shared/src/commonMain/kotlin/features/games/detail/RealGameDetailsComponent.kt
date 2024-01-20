package features.games.detail

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import features.BaseComponent
import features.utils.ModelState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

internal class RealGameDetailsComponent(
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext,
) : BaseComponent(componentContext, coroutineContext), GameDetailsComponent {
    private val modelState: MutableValue<ModelState<GameDetailsModel>> =
        MutableValue(ModelState.Loading())

    override val modelValue: Value<ModelState<GameDetailsModel>> = modelState

    init {
        scope.launch {
            delay(3000)
            modelState.update { ModelState.Success(GameDetailsModel()) }
        }
    }
}
