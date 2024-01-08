package features.login

import com.arkivanov.decompose.ComponentContext
import features.login.LoginComponent
import features.login.LoginModel

internal class RealLoginComponent(
    componentContext: ComponentContext,
) : LoginComponent, ComponentContext by componentContext {
    override val model = LoginModel(title = "Login")
    override fun onLoginClick() {
        print("CLICKED!")
    }
}