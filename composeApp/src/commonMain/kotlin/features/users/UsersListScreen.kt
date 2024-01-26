package features.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.widget.users.Widget
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
            model.users.forEach { user ->
                user.Widget(
                    onEditClick = {
                        component.onEdit()
                    },
                    onDeleteClick = {
                        component.onDelete()
                    },
                )
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}
