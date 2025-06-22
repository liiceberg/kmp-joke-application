package com.itis.joke.feature.auth.presentation.sign_in

import com.itis.joke.core.ui.viewmodel.model.UiEvent

sealed interface SignInEvent : UiEvent {
    data class OnEmailFilled(val email: String) : SignInEvent
    data class OnPasswordFilled(val password: String) : SignInEvent
    data object OnSignIn : SignInEvent
}