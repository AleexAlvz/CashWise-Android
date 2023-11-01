package com.aleexalvz.cashwise.feature.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
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
import com.aleexalvz.cashwise.ui.theme.CashWiseTheme

@Composable
fun HomeNavGraph() {

    val navController: NavHostController = rememberNavController()

    val topBarTitleState =
        remember { mutableStateOf(getTitleByDestination(navController = navController)) }
    navController.addOnDestinationChangedListener { _, _, _ ->
        topBarTitleState.value = getTitleByDestination(navController)
    }

    Scaffold(
        topBar = { TopAppBar(topBarTitleState) },
        bottomBar = { navigationBottomBar(navController) }
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
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
                        onAddTransaction = {
                            navController.navigate(HomeRoutes.ADD_EDIT_TRANSACTION)
                        },
                        onEditTransaction = { transactionId ->
                            navController.navigate(
                                route = "${HomeRoutes.ADD_EDIT_TRANSACTION}/$transactionId"
                            )
                        }
                    )
                }
                composable(
                    route = "${HomeRoutes.ADD_EDIT_TRANSACTION}/{${AddEditTransactionArgs.transactionIDArg}}",
                    arguments = listOf(navArgument(AddEditTransactionArgs.transactionIDArg) {
                        type = NavType.LongType
                    })
                ) { entry ->
                    val id = entry.arguments
                        ?.getLong(AddEditTransactionArgs.transactionIDArg)
                    AddEditTransactionScreen(
                        Modifier.padding(26.dp),
                        transactionId = id,
                        onFinish = {
                            navController.navigate(HomeRoutes.STATEMENT)
                        }
                    )
                }
                composable(route = HomeRoutes.ADD_EDIT_TRANSACTION) {
                    AddEditTransactionScreen(
                        Modifier.padding(26.dp),
                        onFinish = {
                            navController.navigate(HomeRoutes.STATEMENT)
                        }
                    )
                }
                composable(
                    route = HomeRoutes.CALENDAR
                ) {
                    CalendarScreen()
                }
            }
        }
    }
}

private fun getTitleByDestination(navController: NavController): String =
    when (navController.currentDestination?.route) {
        HomeRoutes.STATEMENT -> "Statement"
        HomeRoutes.CALENDAR -> "Calendar"
        HomeRoutes.ADD_EDIT_TRANSACTION -> {
            if (navController.currentBackStackEntry?.arguments?.getString(AddEditTransactionArgs.transactionIDArg)
                    .isNullOrBlank()
            ) {
                "Add transaction"
            } else {
                "Edit transaction"
            }
        }

        else -> "Cash Wise"
    }

@Preview
@Composable
fun HomeNavGraphPreview() {
    CashWiseTheme {
        HomeNavGraph()
    }
}