package com.itis.joke.feature.auth.presentation.sign_up

import com.itis.joke.core.ui.LoadState
import com.itis.joke.core.ui.viewmodel.model.UiState
import com.itis.joke.feature.auth.presentation.util.ValidationResult

data class SignUpState(
    val username: String = "",
    val usernameValidation: ValidationResult = ValidationResult.empty(),
    val email: String = "",
    val emailValidation: ValidationResult = ValidationResult.empty(),
    val password: String = "",
    val passwordValidation: ValidationResult = ValidationResult.empty(),
    val confirmPassword: String = "",
    val confirmPasswordValidation: ValidationResult = ValidationResult.empty(),
    val loadState: LoadState = LoadState.Initial,
) : UiState