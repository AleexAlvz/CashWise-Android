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
import androidx.core.os.bundleOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aleexalvz.cashwise.feature.addedittransaction.AddEditTransactionArgs
import com.aleexalvz.cashwise.feature.addedittransaction.AddEditTransactionScreen
import com.aleexalvz.cashwise.feature.home.HomeRoutes
import com.aleexalvz.cashwise.feature.home.calendar.CalendarScreen
import com.aleexalvz.cashwise.feature.home.home.HomeScreen
import com.aleexalvz.cashwise.feature.home.statement.StatementScreen
import com.aleexalvz.cashwise.ui.theme.DarkBackground

@Composable
fun HomeNavGraph() {

    val navController: NavHostController = rememberNavController()
    val topBarTitle = remember { mutableStateOf("Cash Wise") }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        topBarTitle.value = when (destination.route) {
            HomeRoutes.STATEMENT -> "Statement"
            HomeRoutes.CALENDAR -> "Calendar"
            HomeRoutes.ADD_EDIT_TRANSACTION -> {
                if (navController.currentBackStackEntry?.arguments?.getString(AddEditTransactionArgs.transactionID)
                        ?.isBlank() == true
                ) {
                    "Add transaction"
                } else {
                    "Edit transaction"
                }
            }

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
                    StatementScreen(
                        onClickAddButton = { id ->
                            val bundle = bundleOf(AddEditTransactionArgs.transactionID to id)
                            navController.navigate(
                                route = HomeRoutes.ADD_EDIT_TRANSACTION
                            )
                        }
                    )
                }
                composable(route = HomeRoutes.CALENDAR) {
                    CalendarScreen()
                }
                composable(route = HomeRoutes.ADD_EDIT_TRANSACTION) { entry ->
                    val id =
                        entry.arguments?.getString(AddEditTransactionArgs.transactionID)?.toLong()
                    AddEditTransactionScreen(transactionId = id)
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