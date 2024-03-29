package features.home

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import features.Component
import features.games.detail.OrdersComponent
import features.games.list.GamesListComponent
import features.users.UsersListComponent

interface HomeComponent : Component<HomeModel> {
    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class GamesChild(val component: GamesListComponent) : Child()

        class OrdersChild(val component: OrdersComponent) : Child()

        class UsersChild(val component: UsersListComponent) : Child()
    }
}
