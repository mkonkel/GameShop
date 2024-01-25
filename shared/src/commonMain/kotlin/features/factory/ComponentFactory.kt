package features.factory

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.webhistory.WebHistoryController
import deeplink.DeepLink
import features.RootComponent
import features.games.detail.GameDetailsComponent
import features.games.detail.OrdersComponent
import features.games.list.GamesListComponent
import features.home.HomeComponent
import features.login.LoginComponent
import features.root.login.RegisterComponent
import features.users.UsersListComponent

@OptIn(ExperimentalDecomposeApi::class)
interface ComponentFactory {
    fun createRootComponent(
        deepLink: DeepLink,
        webHistoryController: WebHistoryController?,
        componentContext: ComponentContext,
    ): RootComponent

    fun createRegisterComponent(componentContext: ComponentContext): RegisterComponent

    fun createLoginComponent(componentContext: ComponentContext): LoginComponent

    fun createHomeComponent(componentContext: ComponentContext): HomeComponent

    fun createGamesListComponent(componentContext: ComponentContext): GamesListComponent

    fun createOrdersComponent(componentContext: ComponentContext): OrdersComponent

    fun createGameDetailsComponent(componentContext: ComponentContext): GameDetailsComponent

    fun createUsersListComponent(componentContext: ComponentContext): UsersListComponent
}
