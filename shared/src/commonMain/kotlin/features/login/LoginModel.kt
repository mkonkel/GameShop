package features.login

import features.utils.Model
import widget.button.Button
import widget.input.InputText

data class LoginModel(
    val login: InputText,
    val pass: InputText,
    val loginButton: Button,
    val registerButton: Button,
) : Model
