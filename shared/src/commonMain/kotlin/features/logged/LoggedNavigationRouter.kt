package features.logged

import features.Destination
import features.NavigationRouter
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class LoggedNavigationRouter : NavigationRouter {
    private val destinationsFlow: MutableSharedFlow<Destination> = MutableSharedFlow()

    override fun observeDestinations(): SharedFlow<Destination> {
        return destinationsFlow.asSharedFlow()
    }

    override suspend fun push(destination: Destination) {
        destinationsFlow.emit(destination)
    }

    enum class RootDestination : Destination {
        GAMES,
        USERS,
    }
}
