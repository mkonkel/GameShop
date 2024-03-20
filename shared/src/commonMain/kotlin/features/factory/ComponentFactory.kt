package features.factory

import com.arkivanov.decompose.ComponentContext
import features.games.add.AddGameComponent
import features.games.detail.GameDetailsComponent
import features.games.detail.OrdersComponent
import features.games.list.GamesListComponent
import features.home.HomeComponent
import features.login.LoginComponent
import features.root.login.RegisterComponent
import features.users.UsersListComponent

internal interface ComponentFactory {
    fun createRegisterComponent(componentContext: ComponentContext): RegisterComponent

    fun createLoginComponent(
        componentContext: ComponentContext,
        onLogin: () -> Unit,
        onRegister: () -> Unit,
    ): LoginComponent

    fun createHomeComponent(
        componentContext: ComponentContext,
        onCloseClick: () -> Unit,
        onGamesClick: (String) -> Unit,
        onAddGameClick: () -> Unit,
    ): HomeComponent

    fun createGamesListComponent(
        componentContext: ComponentContext,
        onDetails: (String) -> Unit,
        onAdd: () -> Unit,
    ): GamesListComponent

    fun createOrdersComponent(componentContext: ComponentContext): OrdersComponent

    fun createGameDetailsComponent(
        componentContext: ComponentContext,
        gameId: String,
        onBackClick: () -> Unit,
        onEditClick: () -> Unit,
    ): GameDetailsComponent

    fun createAddGameComponent(
        componentContext: ComponentContext,
        gameId: String? = null,
        onBackClick: () -> Unit,
        onAddGameClick: () -> Unit,
    ): AddGameComponent

    fun createUsersListComponent(componentContext: ComponentContext): UsersListComponent
}
