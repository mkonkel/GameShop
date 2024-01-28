package widget.input.validators

import widget.input.InputText
import widget.input.InputValidator
import widget.input.Validatable

class FormInputValidator(
    val inputText: InputText,
    private val validators: List<InputValidator<String, String>>
) : Validatable<String> {

    override fun validate(): InputValidator.Result<String> {
        return validators.map { validator -> validator.validate(inputText.text.value) }
            .let { results ->
                results.firstOrNull { result -> result is InputValidator.Result.Error }
                    .also {
                        inputText.errorMessage.value = (it as? InputValidator.Result.Error)?.message ?: ""
                    }
                    ?: InputValidator.Result.Success(inputText.text.value)
            }
    }
}