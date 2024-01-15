package features.root.login

import features.Component

interface LoginComponent : Component<RegisterModel> {
    fun onLoginClick()
}
