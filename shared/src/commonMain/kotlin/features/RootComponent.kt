package features

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import features.games.detail.GameDetailsComponent
import features.home.HomeComponent
import features.login.LoginComponent
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
