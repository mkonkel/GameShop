package features.utils

sealed class ModelState<T : Model> {
    class Loading<T : Model> : ModelState<T>()

    data class Success<T : Model>(val model: T) : ModelState<T>()

    data class Error<T : Model>(val message: String) : ModelState<T>()
}
