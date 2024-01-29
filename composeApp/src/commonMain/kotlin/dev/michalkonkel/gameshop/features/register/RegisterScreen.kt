package dev.michalkonkel.gameshop.features.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.michalkonkel.gameshop.ui.widget.input.Widget
import dev.michalkonkel.gameshop.utils.observeModel
import features.root.login.RegisterComponent
import features.root.login.RegisterModel

@Composable
internal fun RegisterScreen(
    component: RegisterComponent,
    modifier: Modifier = Modifier,
) {
    component.observeModel { loginModel ->
        Content(component, loginModel, modifier)
    }
}

@Composable
private fun Content(
    component: RegisterComponent,
    model: RegisterModel,
    modifier: Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Card(
            modifier =
                Modifier
                    .fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
            ) {
                model.name.Widget(Modifier.fillMaxWidth(), Icons.Default.AccountBox)
                Spacer(Modifier.height(16.dp))
                model.email.Widget(Modifier.fillMaxWidth(), Icons.Default.Email)
                Spacer(Modifier.height(16.dp))
                model.pass.Widget(Modifier.fillMaxWidth(), Icons.Default.Lock)
                Spacer(Modifier.height(16.dp))
                model.repeatPass.Widget(Modifier.fillMaxWidth(), Icons.Default.Lock)
                Spacer(Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Button(onClick = { component.onRegisterClick() }) {
                        Text("Register")
                    }
                }
            }
        }
    }
}
