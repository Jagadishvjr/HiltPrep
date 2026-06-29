package com.jagadishvjr.hiltprep.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

private object Routes {
    const val Welcome = "welcome"
    const val Users = "users"
}

@Composable
fun HiltPrepNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Welcome
    ) {
        composable(Routes.Welcome) {
            WelcomeScreen(
                onGetUsersClick = {
                    navController.navigate(Routes.Users)
                }
            )
        }

        composable(Routes.Users) {
            UserRoute()
        }
    }
}
