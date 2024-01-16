package features.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import features.logged.HomeComponent
import features.logged.games.detail.GameDetailsComponent
import features.root.login.LoginComponent
import features.root.login.RegisterComponent

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class LoginChild(val component: LoginComponent) : Child()

        class RegisterChild(val component: RegisterComponent) : Child()

        class HomeChild(val component: HomeComponent) : Child()

        class GameDetails(val component: GameDetailsComponent) : Child()
    }
}
