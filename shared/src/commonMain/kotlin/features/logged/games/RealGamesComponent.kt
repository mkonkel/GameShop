package features.logged.games

import com.arkivanov.decompose.ComponentContext

internal class RealGamesComponent(
    componentContext: ComponentContext,
) : GamesComponent, ComponentContext by componentContext {
    override val model = GamesModel(title = "Games")
}
