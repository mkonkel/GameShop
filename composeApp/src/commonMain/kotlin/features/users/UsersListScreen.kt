package features.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import utils.observeModel

@Composable
internal fun UsersScreen(component: UsersListComponent) {
    component.observeModel { model ->
        Content(component, model)
    }
}

@Composable
private fun Content(
    component: UsersListComponent,
    model: UsersModel,
) {
    Column(
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
