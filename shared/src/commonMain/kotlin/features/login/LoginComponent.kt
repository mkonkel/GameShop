package features.login

interface LoginComponent {
    val model: LoginModel
    fun onLoginClick()
}