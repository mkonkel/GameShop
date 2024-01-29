package features

import com.arkivanov.decompose.value.Value
import features.utils.Model
import features.utils.ModelState

interface Component<T : Model> {
    val modelValue: Value<ModelState<T>>
}
