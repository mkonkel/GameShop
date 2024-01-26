package features.users

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import features.BaseComponent
import features.utils.ModelState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

internal class RealUsersComponent(
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext,
) : BaseComponent(componentContext, coroutineContext), UsersListComponent {
    private val modelState: MutableValue<ModelState<UsersModel>> =
        MutableValue(ModelState.Loading())

    override val modelValue: Value<ModelState<UsersModel>> = modelState

    init {
        scope.launch {
            delay(3000)
            modelState.value = ModelState.Success(UsersModel())
        }
    }

    override fun onEdit() {
    }

    override fun onDelete() {
    }
}
