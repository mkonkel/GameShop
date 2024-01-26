package features.games.detail

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import dev.michalkonkel.gameshop.repository.games.GamesRepository
import features.BaseComponent
import features.utils.ModelState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

internal class RealGameDetailsComponent(
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext,
    private val gamesRepository: GamesRepository,
    private val gameId: String,
    private val onBackClick: () -> Unit,
    private val onEditClick: () -> Unit,
) : BaseComponent(componentContext, coroutineContext), GameDetailsComponent {
    private val modelState: MutableValue<ModelState<GameDetailsModel>> =
        MutableValue(ModelState.Loading())

    override val modelValue: Value<ModelState<GameDetailsModel>> = modelState

    private lateinit var model: GameDetailsModel

    init {
        scope.launch {
            delay(3000)
            try {
                val game = gamesRepository.getGame(gameId)
                if (game == null) {
                    modelState.update { ModelState.Error("Game not found") }
                } else {
                    model = GameDetailsModelMapper.mapModel(game, onBackClick, onEditClick)
                    modelState.update { ModelState.Success(model) }
                }
            } catch (e: Exception) {
                modelState.update { ModelState.Error("Something went wrong") }
            }
        }
    }
}
