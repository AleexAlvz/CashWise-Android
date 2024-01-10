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
import com.aleexalvz.cashwise.feature.investmentsform.InvestmentsFormArgs
import com.aleexalvz.cashwise.feature.investmentsform.InvestmentsFormScreen
import com.aleexalvz.cashwise.feature.home.HomeRoutes
import com.aleexalvz.cashwise.feature.home.calendar.CalendarScreen
import com.aleexalvz.cashwise.feature.home.home.HomeScreen
import com.aleexalvz.cashwise.feature.home.investmentform.StatementScreen
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
                        onAddInvestment = {
                            navController.navigate(HomeRoutes.INVESTMENT_FORM)
                        },
                        onEditInvestment = { investmentId ->
                            navController.navigate(
                                route = "${HomeRoutes.INVESTMENT_FORM}/$investmentId"
                            )
                        }
                    )
                }
                composable(
                    route = "${HomeRoutes.INVESTMENT_FORM}/{${InvestmentsFormArgs.investmentIDArg}}",
                    arguments = listOf(navArgument(InvestmentsFormArgs.investmentIDArg) {
                        type = NavType.LongType
                    })
                ) { entry ->
                    val id = entry.arguments
                        ?.getLong(InvestmentsFormArgs.investmentIDArg)
                    InvestmentsFormScreen(
                        Modifier.padding(26.dp),
                        investmentId = id,
                        onFinish = {
                            navController.popBackStack()
                        }
                    )
                }
                composable(route = HomeRoutes.INVESTMENT_FORM) {
                    InvestmentsFormScreen(
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
        HomeRoutes.INVESTMENT_FORM -> {
            if (navController.currentBackStackEntry?.arguments?.getString(InvestmentsFormArgs.investmentIDArg)
                    .isNullOrBlank()
            ) {
                "Add investment"
            } else {
                "Edit investment"
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