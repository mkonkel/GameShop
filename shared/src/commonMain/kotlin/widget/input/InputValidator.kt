package widget.input

interface InputValidator<I, R> {
    class Required(private val message: String) : InputValidator<String, String> {
        override fun validate(input: String): Result<String> {
            return if (input.isNotEmpty()) {
                Result.Success(input)
            } else {
                Result.Error(message)
            }
        }
    }

    class MinLength(private val minLength: Int, private val message: String) :
        InputValidator<String, String> {
        override fun validate(input: String): Result<String> {
            return if (input.length >= minLength) {
                Result.Success(input)
            } else {
                Result.Error(message)
            }
        }
    }

    class MaxLength(private val maxLength: Int, private val message: String) :
        InputValidator<String, String> {
        override fun validate(input: String): Result<String> {
            return if (input.length <= maxLength) {
                Result.Success(input)
            } else {
                Result.Error(message)
            }
        }
    }

    fun validate(input: I): Result<R>

    sealed class Result<R> {
        data class Success<R>(val value: R) : Result<R>()
        data class Error<R>(val message: String) : Result<R>()
    }
}