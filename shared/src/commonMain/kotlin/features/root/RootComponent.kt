package features.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import features.login.LoginComponent
import features.users.UsersComponent
import features.games.GamesComponent

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class LoginChild(val component: LoginComponent) : Child()
        class GamesChild(val component: GamesComponent) : Child()
        class UsersChild(val component: UsersComponent) : Child()
    }
}