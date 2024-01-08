package features

import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.webhistory.WebHistoryController
import deeplink.DeepLink
import features.games.GamesComponent
import features.login.LoginComponent
import features.root.RootComponent
import features.users.UsersComponent

@OptIn(ExperimentalDecomposeApi::class)
interface ComponentFactory {
    fun createRootComponent(
        deepLink: DeepLink,
        webHistoryController: WebHistoryController?,
    ): RootComponent

    fun createUsersComponent(): UsersComponent

    fun createLoginComponent(): LoginComponent

    fun createGamesComponent(): GamesComponent
}