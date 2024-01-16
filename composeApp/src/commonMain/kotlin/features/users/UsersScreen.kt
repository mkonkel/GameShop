package features.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import features.logged.users.UsersComponent
import features.logged.users.UsersModel
import utils.observeModel

@Composable
internal fun UsersScreen(
    component: UsersComponent,
    modifier: Modifier = Modifier,
) {
    component.observeModel { model, _ ->
        Content(component, model, modifier)
    }
}

@Composable
private fun Content(
    component: UsersComponent,
    model: UsersModel,
    modifier: Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Column {
            model.users.forEach {
                Text(it)
            }
        }
    }
}
