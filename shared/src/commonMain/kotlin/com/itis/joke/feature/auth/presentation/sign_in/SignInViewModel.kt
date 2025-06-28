package com.itis.joke.feature.auth.presentation.sign_in

import androidx.lifecycle.viewModelScope
import com.itis.joke.core.ui.LoadState
import com.itis.joke.core.common.exceptions.AppException
import com.itis.joke.core.common.exceptions.ExceptionHandlerDelegate
import com.itis.joke.core.common.exceptions.runCatching
import com.itis.joke.core.ui.viewmodel.BaseViewModel
import com.itis.joke.feature.auth.domain.usecase.LoginUseCase
import com.itis.joke.feature.auth.presentation.util.UserDataValidator
import com.itis.joke.feature.auth.presentation.util.ValidationResult
import kotlinx.coroutines.launch

class SignInViewModel(
    private val validator: UserDataValidator,
    private val loginUseCase: LoginUseCase,
    private val exceptionHandler: ExceptionHandlerDelegate,
) : BaseViewModel<SignInState, SignInEvent, SignInAction>(
    SignInState()
) {

    override fun obtainEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.OnEmailFilled -> {
                validateEmail(event.email)
            }

            is SignInEvent.OnPasswordFilled -> {
                validatePassword(event.password)
            }

            is SignInEvent.OnSignIn -> {
                if (validateAll()) {
                    login(viewState.email, viewState.password)
                }
            }
        }
    }

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            runCatching(exceptionHandler) {
                viewState = viewState.copy(loadState = LoadState.Loading)
                loginUseCase.invoke(email, password)
            }.onSuccess {
                viewState = viewState.copy(loadState = LoadState.Success)
                viewAction = SignInAction.GoToMainPage
            }.onFailure { ex ->
                if (ex is AppException.InvalidCredentials) {
                    viewState = viewState.copy(loadState = LoadState.Success)
                    viewState = viewState.copy(
                        passwordValidation = ValidationResult(
                            isValid = false,
                            error = ex.message,
                        )
                    )
                } else {
                    ex.message?.let { viewState = viewState.copy(loadState = LoadState.Error(it)) }
                }
            }
        }
    }

    private fun validateAll(): Boolean {
        return validateEmail(viewState.email) && validatePassword(viewState.password)
    }

    private fun validateEmail(email: String): Boolean {
        with(validator.validateEmail(email)) {
            viewState = viewState.copy(
                email = email,
                emailValidation = this
            )
            return isValid
        }
    }

    private fun validatePassword(password: String): Boolean {
        with(validator.validatePasswordNotBlank(password)) {
            viewState = viewState.copy(
                password = password,
                passwordValidation = this
            )
            return isValid
        }
    }

}