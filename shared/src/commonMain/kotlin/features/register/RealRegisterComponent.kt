package features.register

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import features.BaseComponent
import features.NavigationRouter
import features.root.login.RegisterComponent
import features.root.login.RegisterModel
import features.utils.ModelState
import repository.remote.login.LoginRepository
import widget.input.InputText
import kotlin.coroutines.CoroutineContext

internal class RealRegisterComponent(
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext,
    private val navigationRouter: NavigationRouter,
    private val loginRepository: LoginRepository,
) : BaseComponent(componentContext, coroutineContext), RegisterComponent {
    private val modelState: MutableValue<ModelState<RegisterModel>> =
        MutableValue(ModelState.Loading())

    override val modelValue: Value<ModelState<RegisterModel>> = modelState

    private val model: RegisterModel =
        RegisterModel(
            name = InputText(type = InputText.Type.NORMAL, label = "Name"),
            email = InputText(type = InputText.Type.NORMAL, label = "Email"),
            pass = InputText(type = InputText.Type.SECURE, label = "Password"),
            repeatPass = InputText(type = InputText.Type.SECURE, label = "Repeat Password"),
        )

    init {
        modelState.update { ModelState.Success(this.model) }
    }

    override fun onRegisterClick() {
        println("Register clicked!")
    }

    private suspend fun login(
        username: String,
        password: String,
    ) {
        loginRepository.login(username, password)
    }
}
