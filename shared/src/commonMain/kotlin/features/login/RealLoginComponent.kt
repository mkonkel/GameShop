package features.login

import com.arkivanov.decompose.ComponentContext
import features.BaseComponent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import repository.remote.login.LoginRepository

internal class RealLoginComponent(
    componentContext: ComponentContext,
    private val loginRepository: LoginRepository,
) : BaseComponent(componentContext), LoginComponent {

    override val model = LoginModel(title = "Login")

    override fun onLoginClick() {
        println("Login clicked!")
        scope.launch {
            delay(5000)
            println("DELAYED!!!")
            model.test.value = "TEXT AFTER 5s delay!"
            println("VALUE SET!!!")
        }
    }

    private suspend fun login(username: String, password: String) {
        loginRepository.login(username, password)
    }
}
