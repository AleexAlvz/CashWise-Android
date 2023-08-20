package com.aleexalvz.cashwise.login

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun LoginNavGraph() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LoginRoutes.LOGIN
    ) {

        composable(route = LoginRoutes.LOGIN) {
//            loginModalDrawer(
//                LoginRoutes.LOGIN,
//            )
        }
    }
}
