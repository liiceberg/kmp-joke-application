package com.itis.joke.android.ui.navigation

import com.itis.joke.android.R
import com.itis.joke.android.ui.components.BottomNavItem


val bottomNavItems = listOf(
    BottomNavItem(
        icon = R.drawable.random,
        route = getRoute(Route.BottomMenu.RandomJoke),
    ),
    BottomNavItem(
        icon = R.drawable.library,
        route = getRoute(Route.BottomMenu.JokeLibrary),
    ),
    BottomNavItem(
        icon = R.drawable.bulb,
        route = getRoute(Route.BottomMenu.SuggestJoke),
    ),
)

private fun getRoute(route: Route): String {
    return route::class.qualifiedName.toString()
}
