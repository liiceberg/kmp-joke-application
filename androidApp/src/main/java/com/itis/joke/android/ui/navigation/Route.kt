package com.itis.joke.android.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    object BottomMenu {
        @Serializable
        data object JokeLibrary : Route
        @Serializable
        data object RandomJoke : Route
        @Serializable
        data object SuggestJoke : Route
    }

    object Auth {
        @Serializable
        data object SignIn : Route

        @Serializable
        data object SignUp : Route
    }

    @Serializable
    data object JokeSettings : Route

}