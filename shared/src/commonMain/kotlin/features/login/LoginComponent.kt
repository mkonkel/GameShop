package features.login

import features.Component

interface LoginComponent : Component<LoginModel> {
    fun onLoginClick()

    fun onRegisterClick()
}
