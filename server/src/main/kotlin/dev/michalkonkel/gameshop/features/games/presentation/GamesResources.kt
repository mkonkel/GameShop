package dev.michalkonkel.gameshop.features.games.presentation

import dev.michalkonkel.gameshop.repository.games.GamesRepository.Companion.GAMES_ENDPOINT
import io.ktor.resources.Resource
import kotlinx.serialization.Serializable

@Serializable
@Resource(GAMES_ENDPOINT)
class GamesResources {
    @Serializable
    @Resource("{id}")
    class Id(val parent: GamesResources = GamesResources(), val id: String)
}
