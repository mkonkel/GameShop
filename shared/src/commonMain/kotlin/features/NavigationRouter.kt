package features

import kotlinx.coroutines.flow.SharedFlow

interface NavigationRouter {
    fun observeDestinations(): SharedFlow<Destination>

    suspend fun push(destination: Destination)
}

interface Destination
