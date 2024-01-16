package features.logged.factory

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.webhistory.WebHistoryController
import deeplink.DeepLink
import features.logged.HomeComponent
import features.logged.games.detail.GameDetailsComponent
import features.logged.games.list.GamesListComponent
import features.logged.users.UsersListComponent

@OptIn(ExperimentalDecomposeApi::class)
interface LoggedComponentFactory {
    fun createLoggedComponent(
        deepLink: DeepLink,
        webHistoryController: WebHistoryController?,
        componentContext: ComponentContext,
    ): HomeComponent

    fun createGamesListComponent(componentContext: ComponentContext): GamesListComponent

    fun createGameDetailsComponent(componentContext: ComponentContext): GameDetailsComponent

    fun createUsersListComponent(componentContext: ComponentContext): UsersListComponent
}
