package com.aleexalvz.cashwise.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import com.aleexalvz.cashwise.components.topappbar.TopAppBar
import com.aleexalvz.cashwise.components.topappbar.TopAppBarUIState
import com.aleexalvz.cashwise.feature.home.home.HomeScreen
import com.aleexalvz.cashwise.feature.home.ui.navigationBottomBar
import com.aleexalvz.cashwise.feature.investments.InvestmentsScreen
import com.aleexalvz.cashwise.feature.investmentsform.InvestmentsFormArgs
import com.aleexalvz.cashwise.feature.investmentsform.InvestmentsFormScreen
import com.aleexalvz.cashwise.feature.menu.MenuScreen
import com.aleexalvz.cashwise.ui.theme.CashWiseTheme

@Composable
fun HomeNavGraph() {

    val navController: NavHostController = rememberNavController()

    val topAppBarUiState = remember {
        mutableStateOf(TopAppBarUIState())
    }

    Scaffold(
        topBar = {
            TopAppBar(
                uiState = topAppBarUiState.value,
                onBackPressed = { navController.popBackStack() },
                onProfilePressed = {}

            )
        },
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

                    topAppBarUiState.value = TopAppBarUIState()
                    HomeScreen()
                }

                composable(route = HomeRoutes.INVESTMENTS) {

                    topAppBarUiState.value = TopAppBarUIState(
                        title = HomeRoutes.INVESTMENTS,
                        isProfileIconEnabled = true
                    )
                    InvestmentsScreen(
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

                    topAppBarUiState.value = TopAppBarUIState(
                        title = "edit investment",
                        isBackButtonEnabled = true
                    )

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

                    topAppBarUiState.value = TopAppBarUIState(
                        title = "add investment",
                        isBackButtonEnabled = true
                    )

                    InvestmentsFormScreen(
                        Modifier.padding(26.dp),
                        onFinish = {
                            navController.navigate(HomeRoutes.INVESTMENTS)
                        }
                    )
                }

                composable(
                    route = HomeRoutes.MENU
                ) {
                    topAppBarUiState.value = TopAppBarUIState(
                        title = HomeRoutes.MENU,
                        isProfileIconEnabled = true
                    )

                    MenuScreen()
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeNavGraphPreview() {
    CashWiseTheme {
        HomeNavGraph()
    }
}