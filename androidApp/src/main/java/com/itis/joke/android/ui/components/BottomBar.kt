package com.itis.joke.android.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

data class BottomNavItem(
    @DrawableRes val icon: Int,
    val route: String,
)

@Composable
fun BottomNavigationBar(navController: NavHostController, items: List<BottomNavItem>) {

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceContainer
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { navItem ->

            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route)
                },
                icon = {
                    JokeIcon(
                        painter = painterResource(id = navItem.icon),
                        size = 24.dp,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    )
                },
            )
        }
    }
}