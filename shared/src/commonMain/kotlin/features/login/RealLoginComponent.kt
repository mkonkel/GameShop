package features.login

import com.arkivanov.decompose.ComponentContext
import features.BaseComponent
import features.NavigationRouter
import features.root.login.RegisterModel
import features.utils.ModelState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import repository.remote.login.LoginRepository
import widget.input.AuthInput
import kotlin.coroutines.CoroutineContext

internal class RealLoginComponent(
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext,
    private val navigationRouter: NavigationRouter,
    private val loginRepository: LoginRepository,
) : BaseComponent(componentContext, coroutineContext), LoginComponent {
    private val _modelState: MutableStateFlow<ModelState<RegisterModel>> =
        MutableStateFlow(ModelState.Loading())

    private val model: RegisterModel =
        RegisterModel(
            login = AuthInput(AuthInput.Type.LOGIN),
            pass = AuthInput(AuthInput.Type.PASSWORD),
        )

    override val modelState: StateFlow<ModelState<RegisterModel>>
        get() = this._modelState.asStateFlow()

    init {
        _modelState.value = ModelState.Success(model)
    }

    override fun onLoginClick() {
        println("Login clicked!")
        scope.launch {
            navigationRouter.push(NavigationRouter.Destination.HOME)
        }
    }

    private suspend fun login(
        username: String,
        password: String,
    ) {
        loginRepository.login(username, password)
    }
}
