package features

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class NavigationRouter {
    private val destinationsFlow: MutableSharedFlow<Destination> = MutableSharedFlow()

    fun observeDestinations(): SharedFlow<Destination> {
        return destinationsFlow.asSharedFlow()
    }

    suspend fun push(destination: Destination) {
        destinationsFlow.emit(destination)
    }

    enum class Destination {
        LOGIN,
        REGISTER,
        HOME,
        GAME_DETAILS,
    }
}
