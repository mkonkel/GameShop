package features.root.login

import features.utils.Model
import widget.input.InputText

data class RegisterModel(
    val name: InputText,
    val email: InputText,
    val pass: InputText,
    val repeatPass: InputText,
) : Model
