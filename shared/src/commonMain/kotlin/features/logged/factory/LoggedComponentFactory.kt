package features.logged.factory

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
    ): LoggedComponent

    fun createGamesComponent(): GamesComponent

    fun createUsersComponent(): UsersComponent
}
