package com.aleexalvz.cashwise.feature.home.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aleexalvz.cashwise.feature.home.HomeRoutes
import com.aleexalvz.cashwise.feature.login.ui.homeScreen

@Composable
fun homeNavGraph() {

    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = HomeRoutes.HOME) {
        composable(route = HomeRoutes.HOME) {
            homeScreen()
        }
    }
}