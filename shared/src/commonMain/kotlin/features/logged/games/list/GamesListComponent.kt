package features.logged.games.list

import features.Component

interface GamesListComponent : Component<GamesListModel> {
    fun onGameClicked(it: String)
}
