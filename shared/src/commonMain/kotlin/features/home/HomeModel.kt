package features.home

import features.utils.Model
import widget.bottombar.BottomBarModel
import widget.topbar.TopBarModel

data class HomeModel(
    val topBar: TopBarModel,
    val bottomBar: BottomBarModel,
) : Model
