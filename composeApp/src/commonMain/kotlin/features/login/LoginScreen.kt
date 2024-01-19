package features.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import features.root.login.RegisterModel
import features.widget.input.Widget
import utils.observeModel

@Composable
internal fun LoginScreen(
    component: LoginComponent,
    modifier: Modifier = Modifier,
) {
    component.observeModel { loginModel, _ ->
        Content(component, loginModel, modifier)
    }
}

@Composable
private fun Content(
    component: LoginComponent,
    model: RegisterModel,
    modifier: Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Card {
            Column {
                model.login.Widget(Modifier.fillMaxWidth())
                model.login.Widget(Modifier.fillMaxWidth())
                Button(onClick = { component.onLoginClick() }) {
                    Text("Login")
                }
            }
        }
    }
}
