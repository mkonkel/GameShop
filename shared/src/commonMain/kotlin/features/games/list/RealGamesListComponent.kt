package features.games.list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import features.BaseComponent
import features.NavigationRouter
import features.utils.ModelState
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

internal class RealGamesListComponent(
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext,
    private val navigationRouter: NavigationRouter,
) : BaseComponent(componentContext, coroutineContext), GamesListComponent {
    private val modelState: MutableValue<ModelState<GamesListModel>> =
        MutableValue(ModelState.Loading())

    override val modelValue: Value<ModelState<GamesListModel>> = modelState

    private val model: GamesListModel = GamesListModel()

    init {
        scope.launch {
            modelState.update { ModelState.Success(model) }
        }
    }

    override fun onGameClicked(it: String) {
        scope.launch {
            navigationRouter.push(NavigationRouter.Destination.GAME_DETAILS)
        }
    }
}
