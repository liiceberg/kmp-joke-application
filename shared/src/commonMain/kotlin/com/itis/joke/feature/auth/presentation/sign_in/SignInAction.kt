package com.itis.joke.feature.auth.presentation.sign_in

import com.itis.joke.core.ui.viewmodel.model.UiAction

sealed class SignInAction : UiAction {
    data object GoToMainPage: SignInAction()
}