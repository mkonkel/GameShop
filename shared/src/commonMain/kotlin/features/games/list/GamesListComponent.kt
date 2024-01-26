package features.games.list

import features.Component

interface GamesListComponent : Component<GamesListModel> {
    fun onGameClicked(id: String)

    fun onAddClicked()
}
