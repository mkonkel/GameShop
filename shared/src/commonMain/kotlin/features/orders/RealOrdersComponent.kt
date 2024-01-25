package features.orders

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import features.BaseComponent
import features.games.detail.OrdersComponent
import features.games.detail.OrdersModel
import features.utils.ModelState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

internal class RealOrdersComponent(
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext,
) : BaseComponent(componentContext, coroutineContext), OrdersComponent {
    private val modelState: MutableValue<ModelState<OrdersModel>> =
        MutableValue(ModelState.Loading())

    override val modelValue: Value<ModelState<OrdersModel>> = modelState

    init {
        scope.launch {
            delay(1000)
            modelState.update { ModelState.Success(OrdersModel()) }
        }
    }
}
