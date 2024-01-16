package features.logged.factory

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.webhistory.WebHistoryController
import deeplink.DeepLink
import features.logged.LoggedComponent
import features.logged.games.GamesComponent
import features.logged.users.UsersComponent

@OptIn(ExperimentalDecomposeApi::class)
interface LoggedComponentFactory {
    fun createLoggedComponent(
        deepLink: DeepLink,
        webHistoryController: WebHistoryController?,
        componentContext: ComponentContext,
    ): LoggedComponent

    fun createGamesComponent(componentContext: ComponentContext): GamesComponent

    fun createUsersComponent(componentContext: ComponentContext): UsersComponent
}
