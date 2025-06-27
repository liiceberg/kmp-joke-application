package com.itis.joke.android.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.itis.joke.android.ui.navigation.Route
import com.itis.joke.android.ui.screens.auth.SignInScreen
import com.itis.joke.android.ui.screens.auth.SignUpScreen
import com.itis.joke.android.ui.screens.library.JokeLibraryScreen
import com.itis.joke.android.ui.screens.random_joke.RandomJokeScreen
import com.itis.joke.android.ui.screens.settings.JokeSettingsScreen
import com.itis.joke.android.ui.screens.suggest_joke.SuggestJokeScreen
import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(ExperimentalSerializationApi::class)
@Composable
fun JokeNavHost(navController: NavHostController, bottomVisibilityChange: (Boolean) -> Unit) {
    NavHost(
        navController = navController,
        startDestination = Route.Auth.SignIn,
    ) {

        composable<Route.BottomMenu.RandomJoke> {
            bottomVisibilityChange(true)
            RandomJokeScreen { navController.navigate(Route.JokeSettings) }
        }
        composable<Route.BottomMenu.JokeLibrary> {
            bottomVisibilityChange(true)
            JokeLibraryScreen()
        }
        composable<Route.BottomMenu.SuggestJoke> {
            bottomVisibilityChange(true)
            SuggestJokeScreen()
        }

        composable<Route.Auth.SignIn> {
            bottomVisibilityChange(false)
            SignInScreen(
                toSignUp = { navController.navigate(Route.Auth.SignUp) },
                toMainPage = { navController.navigate(Route.BottomMenu.JokeLibrary) }
            )
        }
        composable<Route.Auth.SignUp> {
            bottomVisibilityChange(false)
            SignUpScreen {
                navController.navigateUp()
            }
        }

        composable<Route.JokeSettings> {
            bottomVisibilityChange(false)
            JokeSettingsScreen { navController.navigateUp() }
        }
    }
}