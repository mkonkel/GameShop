package features.login

import features.utils.Model
import widget.input.InputText

data class LoginModel(
    val login: InputText,
    val pass: InputText,
) : Model
