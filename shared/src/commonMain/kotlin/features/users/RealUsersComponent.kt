package features.users

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import dev.michalkonkel.gameshop.repository.users.UsersRepository
import features.BaseComponent
import features.utils.ModelState
import kotlinx.coroutines.launch

internal class RealUsersComponent(
    componentContext: ComponentContext,
    private val usersRepository: UsersRepository,
) : BaseComponent(componentContext), UsersListComponent {
    private val modelState: MutableValue<ModelState<UsersModel>> =
        MutableValue(ModelState.Loading())

    override val modelValue: Value<ModelState<UsersModel>> = modelState

    private lateinit var model: UsersModel

    init {
        scope.launch {
            val users = usersRepository.getUsers()
            model = UsersModelMapper.mapModel(users)
            modelState.value = ModelState.Success(model)
        }
    }

    override fun onEdit() {
    }

    override fun onDelete() {
    }
}
