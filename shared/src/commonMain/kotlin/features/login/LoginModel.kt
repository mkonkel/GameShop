package features.login

import com.arkivanov.decompose.value.MutableValue
import kotlinx.coroutines.flow.MutableStateFlow

data class LoginModel(
    val title: String,
    var test: MutableValue<String> = MutableValue("")
)
