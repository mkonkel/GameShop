package features.login

import features.Component
import features.root.login.RegisterModel

interface LoginComponent : Component<RegisterModel> {
    fun onLoginClick()
}
