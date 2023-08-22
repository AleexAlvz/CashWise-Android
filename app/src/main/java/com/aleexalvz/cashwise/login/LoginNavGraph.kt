package com.aleexalvz.cashwise.login

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aleexalvz.cashwise.login.ui.loginAndSignupScreen

@Composable
fun LoginNavGraph() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LoginRoutes.LOGIN
    ) {

        composable(route = LoginRoutes.LOGIN) {
            loginAndSignupScreen(route = LoginRoutes.LOGIN)
        }
        composable(route = LoginRoutes.SIGNUP) {
            loginAndSignupScreen(route = LoginRoutes.SIGNUP)
        }
    }
}