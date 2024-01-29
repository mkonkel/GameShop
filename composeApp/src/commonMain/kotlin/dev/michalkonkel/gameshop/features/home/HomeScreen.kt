package dev.michalkonkel.gameshop.features.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import dev.michalkonkel.gameshop.features.games.GamesScreen
import dev.michalkonkel.gameshop.features.orders.OrdersScreen
import dev.michalkonkel.gameshop.features.users.UsersScreen
import dev.michalkonkel.gameshop.ui.widget.Widget
import dev.michalkonkel.gameshop.utils.observeModel
import features.home.HomeComponent

@Composable
internal fun HomeScreen(
    component: HomeComponent,
    modifier: Modifier = Modifier,
) {
    component.observeModel { model ->
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { model.topBar.Widget() },
            bottomBar = { model.bottomBar.Widget() },
        ) { paddingValues ->
            Column(modifier = Modifier.padding(8.dp)) {
                Children(component, paddingValues)
            }
        }
    }
}

@Composable
private fun Children(
    component: HomeComponent,
    paddingValues: PaddingValues,
) {
    Children(
        modifier = Modifier.padding(paddingValues),
        stack = component.childStack,
        animation = stackAnimation(fade()),
    ) {
        when (val child = it.instance) {
            is HomeComponent.Child.GamesChild -> GamesScreen(child.component)
            is HomeComponent.Child.UsersChild -> UsersScreen(child.component)
            is HomeComponent.Child.OrdersChild -> OrdersScreen(child.component)
        }
    }
}
