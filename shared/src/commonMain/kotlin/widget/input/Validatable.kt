package widget.input

interface Validatable<T> {
    fun validate(): InputValidator.Result<T>
}
