package dev.michalkonkel.gameshop.ui.widget

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import widget.topbar.TopBarModel
import widget.topbar.TopBarModel.Icon.BACK
import widget.topbar.TopBarModel.Icon.CLOSE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarModel.Widget() {
    TopAppBar(
        colors =
            TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
        title = { Text(text = title.value) },
        navigationIcon = {
            IconButton(onClick = navigationAction) {
                when (navigationIcon) {
                    CLOSE -> Icon(imageVector = Icons.Default.Close, contentDescription = null)
                    BACK -> Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        },
        actions = {
            editAction?.let {
                IconButton(onClick = { it() }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                }
            }
        },
    )
}
