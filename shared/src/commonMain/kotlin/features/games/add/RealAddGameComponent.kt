package features.games.add

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import dev.michalkonkel.gameshop.domain.games.AddGameRequest
import dev.michalkonkel.gameshop.repository.games.GamesRepository
import features.BaseComponent
import features.utils.ModelState
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

internal class RealAddGameComponent(
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext,
    private val gamesRepository: GamesRepository,
    private val gameId: String?,
    private val onBackClick: () -> Unit,
    private val onAddGameClick: () -> Unit,
) : BaseComponent(componentContext, coroutineContext), AddGameComponent {
    private val modelState: MutableValue<ModelState<AddGameModel>> =
        MutableValue(ModelState.Loading())

    override val modelValue: Value<ModelState<AddGameModel>> = modelState

    private lateinit var model: AddGameModel

    init {
        if (gameId == null) {
            model = AddGameModelMapper.mapModel(null, onBackClick) { addGame() }
            modelState.update { ModelState.Success(model) }
        } else {
            scope.launch {
                try {
                    val game = gamesRepository.getGame(gameId)
                    if (game == null) {
                        modelState.update { ModelState.Error("Game not found") }
                    } else {
                        model = AddGameModelMapper.mapModel(game, onBackClick) { addGame() }
                        modelState.update { ModelState.Success(model) }
                    }
                } catch (e: Exception) {
                    modelState.update { ModelState.Error("Something went wrong") }
                }
            }
        }
    }

    private fun addGame() {
        try {
            val request =
                AddGameRequest(
                    name = model.content.name.text.value,
                    price = model.content.price.text.value,
                    description = model.content.description.text.value,
                    imageUrl = model.content.image.text.value,
                )

            scope.launch {
                gamesRepository.addGame(request)
                onAddGameClick()
            }
        } catch (e: Exception) {
            println("$e")
        }
    }
}
