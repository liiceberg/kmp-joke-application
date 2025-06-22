package com.itis.joke.feature.auth.presentation.sign_in

import com.itis.joke.core.ui.LoadState
import com.itis.joke.core.ui.viewmodel.model.UiState
import com.itis.joke.feature.auth.presentation.util.ValidationResult

data class SignInState(
    val email: String = "",
    val emailValidation: ValidationResult = ValidationResult.empty(),
    val password: String = "",
    val passwordValidation: ValidationResult = ValidationResult.empty(),
    val loadState: LoadState = LoadState.Initial,
) : UiState