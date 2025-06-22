package com.itis.joke.feature.auth.presentation.sign_up

import com.itis.joke.core.ui.viewmodel.model.UiEvent

sealed interface SignUpEvent : UiEvent {
    data class OnUsernameFilled(val username: String) : SignUpEvent
    data class OnEmailFilled(val email: String) : SignUpEvent
    data class OnPasswordFilled(val password: String) : SignUpEvent
    data class OnConfirmPasswordFilled(val password: String) : SignUpEvent
    data object OnSignUp : SignUpEvent
}