package features.login

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import features.BaseComponent
import features.utils.ModelState
import repository.remote.login.LoginRepository
import widget.button.Button
import widget.input.InputText
import kotlin.coroutines.CoroutineContext

internal class RealLoginComponent(
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext,
    private val loginRepository: LoginRepository,
    private val onLogin: () -> Unit,
    private val onRegister: () -> Unit,
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
            loginButton =
                Button(
                    style = Button.Style.FILLED,
                    text = "Login",
                    onClick = { onLoginClick() },
                ),
            registerButton =
                Button(
                    style = Button.Style.OUTLINED,
                    text = "Register",
                    onClick = { onRegisterClick() },
                ),
        )

    init {
        modelState.update { ModelState.Success(model) }
    }

    override fun onLoginClick() {
        println("Login clicked!")
        println("Login: ${this.model.login.text.value}")
        println("Pass: ${this.model.pass.text.value}")
        onLogin()
    }

    override fun onRegisterClick() {
        onRegister()
    }

    private suspend fun login(
        username: String,
        password: String,
    ) {
        loginRepository.login(username, password)
    }
}
