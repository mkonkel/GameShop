package features.login

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import features.BaseComponent
import features.NavigationRouter
import features.utils.ModelState
import kotlinx.coroutines.launch
import repository.remote.login.LoginRepository
import widget.input.InputText
import kotlin.coroutines.CoroutineContext

internal class RealLoginComponent(
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext,
    private val navigationRouter: NavigationRouter,
    private val loginRepository: LoginRepository,
) : BaseComponent(componentContext, coroutineContext), LoginComponent {
    private val modelState: MutableValue<ModelState<LoginModel>> =
        MutableValue(ModelState.Loading())

    override val modelValue: Value<ModelState<LoginModel>> = modelState

    val model =
        LoginModel(
            login =
                InputText(
                    type = InputText.Type.NORMAL,
                    label = "Login",
                ),
            pass =
                InputText(
                    type = InputText.Type.SECURE,
                    label = "Password",
                ),
        )

    init {
        modelState.update { ModelState.Success(model) }
    }

    override fun onLoginClick() {
        println("Login clicked!")
        println("Login: ${this.model.login.text.value}")
        println("Pass: ${this.model.pass.text.value}")
        scope.launch {
            navigationRouter.push(NavigationRouter.Destination.HOME)
        }
    }

    override fun onRegisterClick() {
        scope.launch {
            navigationRouter.push(NavigationRouter.Destination.REGISTER)
        }
    }

    private suspend fun login(
        username: String,
        password: String,
    ) {
        loginRepository.login(username, password)
    }
}
