package features.games.list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import dev.michalkonkel.gameshop.domain.user.User
import dev.michalkonkel.gameshop.repository.games.GamesRepository
import features.BaseComponent
import features.utils.ModelState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class RealGamesListComponent(
    componentContext: ComponentContext,
    private val gamesRepository: GamesRepository,
    private val onGameDetails: (String) -> Unit,
    private val onAddGame: () -> Unit,
    private val user: User?,
) : BaseComponent(componentContext), GamesListComponent {
    private val modelState: MutableValue<ModelState<GamesListModel>> =
        MutableValue(ModelState.Loading())

    override val modelValue: Value<ModelState<GamesListModel>> = modelState

    private lateinit var model: GamesListModel

    init {
        scope.launch {
            delay(3000)
            try {
                val games = gamesRepository.getGames()
                model = GamesModelMapper.mapModel(games, user, ::onAddClicked)
                modelState.update { ModelState.Success(model) }
            } catch (e: Exception) {
                modelState.update { ModelState.Error("Something went wrong") }
            }
        }
    }

    override fun onGameClicked(id: String) {
        onGameDetails(id)
    }

    override fun onAddClicked() {
        onAddGame()
    }
}
