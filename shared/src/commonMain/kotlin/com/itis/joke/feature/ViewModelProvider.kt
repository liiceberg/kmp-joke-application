package com.itis.joke.feature

import com.itis.joke.feature.auth.presentation.sign_in.SignInViewModel
import com.itis.joke.feature.auth.presentation.sign_up.SignUpViewModel
import com.itis.joke.feature.joke_settings.presenation.JokeSettingsViewModel
import com.itis.joke.feature.library.presenation.JokeLibraryViewModel
import com.itis.joke.feature.random_joke.presentation.RandomJokeViewModel
import com.itis.joke.feature.suggest_joke.domain.SubmitJokeModel
import com.itis.joke.feature.suggest_joke.presentation.SuggestJokeViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

object ViewModelProvider: KoinComponent {

    fun getSignInViewModel(): SignInViewModel = get()

    fun getSignUpViewModel(): SignUpViewModel = get()

    fun getRandomJokeViewModel(): RandomJokeViewModel = get()

    fun getLibraryViewModel(): JokeLibraryViewModel = get()

    fun getSettingsViewModel(): JokeSettingsViewModel = get()

    fun getSuggestJokeViewModel(): SuggestJokeViewModel = get()
}

