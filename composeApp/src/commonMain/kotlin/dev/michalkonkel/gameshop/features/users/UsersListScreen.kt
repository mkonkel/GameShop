package dev.michalkonkel.gameshop.features.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.michalkonkel.gameshop.ui.widget.users.Widget
import dev.michalkonkel.gameshop.utils.observeModel
import features.users.UsersListComponent
import features.users.UsersModel

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
        modifier = Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
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
