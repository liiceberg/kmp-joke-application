package com.itis.joke.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.itis.joke.android.ui.components.BottomNavigationBar
import com.itis.joke.android.ui.components.JokeNavHost
import com.itis.joke.android.ui.navigation.bottomNavItems
import com.itis.joke.android.ui.theme.JokeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JokeTheme {
                val navController = rememberNavController()
                var bottomBarVisible by remember { mutableStateOf(true) }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = {
                            if (bottomBarVisible) {
                                BottomNavigationBar(
                                    navController = navController,
                                    items = bottomNavItems,
                                )
                            }
                        },
                        content = { paddingValues ->
                            Box(modifier = Modifier.padding(paddingValues)) {
                                JokeNavHost(navController) { isVisible ->
                                    bottomBarVisible = isVisible
                                }
                            }
                        }
                    )
                }
            }
        }
    }

}

