package com.aleexalvz.cashwise.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aleexalvz.cashwise.feature.home.HomeRoutes
import com.aleexalvz.cashwise.feature.home.calendar.CalendarScreen
import com.aleexalvz.cashwise.feature.home.home.HomeScreen
import com.aleexalvz.cashwise.feature.home.statement.StatementScreen
import com.aleexalvz.cashwise.ui.theme.DarkBackground

@Composable
fun HomeNavGraph() {

    val navController: NavHostController = rememberNavController()
    val topBarTitle = remember { mutableStateOf("Cash Wise")}

    navController.addOnDestinationChangedListener { _, destination, _ ->
        topBarTitle.value = when (destination.route) {
            HomeRoutes.STATEMENT -> "Statement"
            HomeRoutes.CALENDAR -> "Calendar"
            else -> "Cash Wise"
        }
    }

    Scaffold(
        topBar = { topAppBar(topBarTitle) },
        bottomBar = { navigationBottomBar(navController) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .background(DarkBackground)
        ) {
            NavHost(
                navController = navController,
                startDestination = HomeRoutes.HOME
            ) {
                composable(route = HomeRoutes.HOME) {
                    HomeScreen()
                }
                composable(route = HomeRoutes.STATEMENT) {
                    StatementScreen()
                }
                composable(route = HomeRoutes.CALENDAR) {
                    CalendarScreen()
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeNavGraphPreview() {
    HomeNavGraph()
}