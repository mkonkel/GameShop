package features.users

import com.arkivanov.decompose.ComponentContext

internal class RealUsersComponent(
    componentContext: ComponentContext,
) : UsersComponent, ComponentContext by componentContext {
    override val model = UsersModel(title = "Login")
}