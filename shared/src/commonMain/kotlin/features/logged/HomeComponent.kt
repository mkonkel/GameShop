package features.logged

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import features.Component
import features.logged.games.list.GamesListComponent
import features.logged.users.UsersListComponent

interface HomeComponent : Component<HomeModel> {
    val childStack: Value<ChildStack<*, Child>>

    fun onUsersClick()

    fun onGamesClick()

    sealed class Child {
        class GamesChild(val component: GamesListComponent) : Child()

        class UsersChild(val component: UsersListComponent) : Child()
    }
}
