package com.itis.joke.feature.auth.presentation.sign_up

import com.itis.joke.core.ui.viewmodel.model.UiAction

sealed interface SignUpAction : UiAction {
    data object RedirectOnSuccess : SignUpAction
}