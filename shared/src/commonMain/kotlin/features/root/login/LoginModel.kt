package features.root.login

import features.utils.Model
import widget.input.AuthInput

data class LoginModel(
    val login: AuthInput,
    val pass: AuthInput,
) : Model
