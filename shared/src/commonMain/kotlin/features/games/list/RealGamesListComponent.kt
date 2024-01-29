package features.games.list

import com.arkivanov.decompose.ComponentContext
import features.BaseComponent
import features.NavigationRouter
import features.utils.ModelState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

internal class RealGamesListComponent(
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext,
    private val navigationRouter: NavigationRouter,
) : BaseComponent(componentContext, coroutineContext), GamesListComponent {
    private val _modelState: MutableStateFlow<ModelState<GamesListModel>> =
        MutableStateFlow(ModelState.Loading())

    private val model: GamesListModel = GamesListModel()

    override val modelState: StateFlow<ModelState<GamesListModel>>
        get() = this._modelState.asStateFlow()

    init {
        scope.launch {
            delay(3000)
            _modelState.value = ModelState.Success(model)
        }
    }

    override fun onGameClicked(it: String) {
        scope.launch {
            navigationRouter.push(NavigationRouter.Destination.GAME_DETAILS)
        }
    }
}
