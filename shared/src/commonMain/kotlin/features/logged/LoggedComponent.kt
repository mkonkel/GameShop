package features.logged

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import features.Component
import features.logged.games.GamesComponent
import features.logged.users.UsersComponent

interface LoggedComponent : Component<HomeModel> {
    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class GamesChild(val component: GamesComponent) : Child()

        class UsersChild(val component: UsersComponent) : Child()
    }
}
