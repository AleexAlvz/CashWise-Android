package com.aleexalvz.cashwise.feature.home.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aleexalvz.cashwise.feature.home.HomeRoutes
import com.aleexalvz.cashwise.feature.home.navigationBottomBar
import com.aleexalvz.cashwise.feature.home.topAppBar
import com.aleexalvz.cashwise.feature.login.ui.homeScreen

@Composable
fun homeNavGraph() {

    val navController: NavHostController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = {
            NavHost(navController = navController, startDestination = HomeRoutes.HOME) {
                composable(route = HomeRoutes.HOME) {
                    homeScreen()
                }
            }
        },
        topBar = { topAppBar() },
        bottomBar = { navigationBottomBar(navController) }
    )
}