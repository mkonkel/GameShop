package dev.michalkonkel.gameshop.features.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.michalkonkel.gameshop.ui.widget.Widget
import dev.michalkonkel.gameshop.ui.widget.input.Widget
import dev.michalkonkel.gameshop.utils.observeModel
import features.login.LoginComponent
import features.login.LoginModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun LoginScreen(
    component: LoginComponent,
    modifier: Modifier = Modifier,
) {
    component.observeModel { loginModel ->
        Content(component, loginModel, modifier)
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun Content(
    component: LoginComponent,
    model: LoginModel,
    modifier: Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource("space-dogfighter-logo.png"),
            modifier = Modifier.size(180.dp),
            contentDescription = "space-dogfighter-logo",
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Text(
            text = "GAME SHOP!",
            color = Color(0xFFFF9966),
            fontWeight = FontWeight.Black,
            fontSize = 30.sp,
        )
        Spacer(modifier = Modifier.padding(20.dp))
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(8.dp),
            ) {
                model.login.Widget(Modifier.fillMaxWidth(), Icons.Default.AccountBox)
                Spacer(Modifier.height(16.dp))
                model.pass.Widget(Modifier.fillMaxWidth(), Icons.Default.Lock)
                Spacer(Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    model.loginButton.Widget()
                    model.registerButton.Widget()
                }
            }
        }
    }
}
