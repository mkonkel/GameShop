package features.games.detail

import com.arkivanov.decompose.ComponentContext
import features.BaseComponent
import features.utils.ModelState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

internal class RealGameDetailsComponent(
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext,
) : BaseComponent(componentContext, coroutineContext), GameDetailsComponent {
    private val _modelState: MutableStateFlow<ModelState<GameDetailsModel>> =
        MutableStateFlow(ModelState.Loading())

    private val model: GameDetailsModel = GameDetailsModel()

    override val modelState: StateFlow<ModelState<GameDetailsModel>>
        get() = this._modelState.asStateFlow()

    init {
        scope.launch {
            delay(3000)
            _modelState.value = ModelState.Success(model)
        }
    }
}
