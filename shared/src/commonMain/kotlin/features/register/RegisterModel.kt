package features.root.login

import features.utils.Model
import widget.input.AuthInput

data class RegisterModel(
    val login: AuthInput,
    val pass: AuthInput,
) : Model
