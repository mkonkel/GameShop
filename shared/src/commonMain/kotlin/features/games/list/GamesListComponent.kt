package features.games.list

import features.Component

interface GamesListComponent : Component<GamesListModel> {
    fun onGameClicked(it: String)
}
