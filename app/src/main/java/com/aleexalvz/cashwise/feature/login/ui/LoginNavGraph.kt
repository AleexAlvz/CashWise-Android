package com.aleexalvz.cashwise.feature.login.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aleexalvz.cashwise.feature.login.LoginRoutes

@Composable
fun LoginNavGraph(
    onLoginSuccessful: () -> Unit
) {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LoginRoutes.LOGIN
    ) {
        composable(route = LoginRoutes.LOGIN) {
            LoginAndSignupScreen(
                onLoginSuccessful = onLoginSuccessful
            )
        }
    }
}
