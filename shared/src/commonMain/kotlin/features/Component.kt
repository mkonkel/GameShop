package features

import features.utils.Model
import features.utils.ModelState
import kotlinx.coroutines.flow.StateFlow

interface Component<T : Model> {
    val modelState: StateFlow<ModelState<T>>
}
