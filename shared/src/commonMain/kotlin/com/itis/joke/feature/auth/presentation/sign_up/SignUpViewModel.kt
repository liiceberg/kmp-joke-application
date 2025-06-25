package com.itis.joke.feature.auth.presentation.sign_up

import androidx.lifecycle.viewModelScope
import com.itis.joke.core.ui.LoadState
import com.itis.joke.core.common.exceptions.AppException
import com.itis.joke.core.common.exceptions.ExceptionHandlerDelegate
import com.itis.joke.core.common.exceptions.runCatching
import com.itis.joke.core.ui.viewmodel.BaseViewModel
import com.itis.joke.feature.auth.domain.usecase.RegisterUseCase
import com.itis.joke.feature.auth.presentation.util.UserDataValidator
import com.itis.joke.feature.auth.presentation.util.ValidationResult
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val validator: UserDataValidator,
    private val registerUseCase: RegisterUseCase,
    private val exceptionHandler: ExceptionHandlerDelegate,
) : BaseViewModel<SignUpState, SignUpEvent, SignUpAction>(
    SignUpState()
) {

    override fun obtainEvent(event: SignUpEvent) {

        when (event) {
            is SignUpEvent.OnUsernameFilled -> {
                validateUsername(event.username)
            }

            is SignUpEvent.OnEmailFilled -> {
                validateEmail(event.email)
            }

            is SignUpEvent.OnPasswordFilled -> {
                validatePassword(event.password)
            }

            is SignUpEvent.OnConfirmPasswordFilled -> {
                validateConfirmPassword(event.password)
            }

            is SignUpEvent.OnSignUp -> {
                if (validateAll()) {
                    register(
                        viewState.username, viewState.email, viewState.password
                    )
                }
            }
        }
    }



    private fun register(username: String, email: String, password: String) {
        viewModelScope.launch {
            runCatching(exceptionHandler) {
                viewState = viewState.copy(loadState = LoadState.Loading)
                registerUseCase.invoke(username, email, password)
            }.onSuccess {
                viewState = viewState.copy(loadState = LoadState.Success)
                viewAction = SignUpAction.RedirectOnSuccess
            }.onFailure { ex ->
                if (ex is AppException.SuchEmailAlreadyRegistered) {
                    viewState = viewState.copy(loadState = LoadState.Success)
                    viewState = viewState.copy(
                        emailValidation = ValidationResult(
                            isValid = false,
                            error = "Such email already registered"
                        )
                    )
                } else {
                    ex.message?.let { viewState = viewState.copy(loadState = LoadState.Error(it)) }
                }
            }
        }
    }

    private fun validateAll(): Boolean {
        return validateUsername(viewState.username)
                && validateEmail(viewState.email)
                && validatePassword(viewState.password)
                && validateConfirmPassword(viewState.confirmPassword)
    }

    private fun validateUsername(username: String): Boolean {
        with(validator.validateName(username)) {
            viewState = viewState.copy(
                username = username,
                usernameValidation = this
            )
            return isValid
        }
    }

    private fun validateEmail(email: String): Boolean {
        with(validator.validateEmail(email)) {
            viewState = viewState.copy(
                email = email,
                emailValidation = validator.validateEmail(email)
            )
            return isValid
        }
    }

    private fun validatePassword(password: String): Boolean {
        with(validator.validatePassword(password)) {
            viewState = viewState.copy(
                password = password,
                passwordValidation = this
            )
            return isValid
        }
    }

    private fun validateConfirmPassword(confirmPassword: String): Boolean {
        with(validator.validateConfirmPassword(viewState.password, confirmPassword)) {
            viewState = viewState.copy(
                confirmPassword = confirmPassword,
                confirmPasswordValidation = this
            )
            return isValid
        }
    }

}