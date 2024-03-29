package features.login

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import features.BaseComponent
import features.utils.ModelState
import kotlinx.coroutines.launch
import repository.local.user.UserHolder
import repository.remote.login.LoginRepository
import widget.button.Button
import widget.input.InputText
import widget.input.InputValidator
import widget.input.validators.FormInputValidator

internal class RealLoginComponent(
    componentContext: ComponentContext,
    private val loginRepository: LoginRepository,
    private val onLogin: () -> Unit,
    private val onRegister: () -> Unit,
    private val userHolder: UserHolder,
) : BaseComponent(componentContext), LoginComponent {
    private val modelState: MutableValue<ModelState<LoginModel>> =
        MutableValue(ModelState.Loading())

    override val modelValue: Value<ModelState<LoginModel>> = modelState
    private val loginValidation =
        FormInputValidator(
            inputText =
                InputText(
                    type = InputText.Type.NORMAL,
                    label = "Login",
                ),
            validators =
                listOf(
                    InputValidator.Required("Field is required"),
                    InputValidator.MinLength(3, "Field must be at least 3 characters long"),
                    InputValidator.MaxLength(20, "Field must be at most 20 characters long"),
                ),
        )
    private val passwordValidation =
        FormInputValidator(
            inputText =
                InputText(
                    type = InputText.Type.SECURE,
                    label = "Password",
                ),
            validators =
                listOf(
                    InputValidator.Required("Field is required"),
                    InputValidator.MinLength(3, "Field must be at least 3 characters long"),
                    InputValidator.MaxLength(20, "Field must be at most 20 characters long"),
                ),
        )

    val model =
        LoginModel(
            login = loginValidation.inputText,
            pass = passwordValidation.inputText,
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
        val login = loginValidation.validate()
        val pass = passwordValidation.validate()

        if (login is InputValidator.Result.Success && pass is InputValidator.Result.Success) {
            scope.launch {
                try {
                    val loginResponse = loginRepository.login(login.value, pass.value)
                    userHolder.user = loginResponse?.user

                    onLogin()
                } catch (e: Exception) {
                    modelState.update { ModelState.Error(e.message ?: "Unknown error") }
                }
            }
        }
    }

    override fun onRegisterClick() {
        onRegister()
    }
}
