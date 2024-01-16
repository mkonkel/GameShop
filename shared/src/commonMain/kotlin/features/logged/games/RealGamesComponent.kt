package features.logged.games

import com.arkivanov.decompose.ComponentContext
import features.BaseComponent
import features.utils.ModelState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

internal class RealGamesComponent(
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext,
) : BaseComponent(componentContext, coroutineContext), GamesComponent {
    private val _modelState: MutableStateFlow<ModelState<GamesModel>> =
        MutableStateFlow(ModelState.Loading())

    private val model: GamesModel = GamesModel()

    override val modelState: StateFlow<ModelState<GamesModel>>
        get() = this._modelState.asStateFlow()

    init {
        scope.launch {
            delay(3000)
            _modelState.value = ModelState.Success(model)
        }
    }
}
