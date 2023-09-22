package com.aleexalvz.cashwise.feature.home.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Note
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.aleexalvz.cashwise.feature.home.HomeRoutes
import com.aleexalvz.cashwise.foundation.UserManager
import com.aleexalvz.cashwise.ui.theme.BottomAppBarDarkColor
import com.aleexalvz.cashwise.ui.theme.IndicatorItemColor

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector
)

private fun getBottomNavItems(): List<BottomNavItem> = listOf(
    BottomNavItem("Home", HomeRoutes.HOME, Icons.Filled.Home),
    BottomNavItem("Statement", HomeRoutes.STATEMENT, Icons.Filled.Note),
    BottomNavItem("Calendar", HomeRoutes.CALENDAR, Icons.Filled.CalendarMonth)
)

@Composable
fun navigationBottomBar(navController: NavController) {

    val backStackEntry = navController.currentBackStackEntryAsState()

    NavigationBar(containerColor = BottomAppBarDarkColor) {
        getBottomNavItems().forEach { item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.White,
                    indicatorColor = IndicatorItemColor,
                    selectedTextColor = Color.White,
                    unselectedTextColor = Color.White
                ),
                selected = item.route == backStackEntry.value?.destination?.route,
                onClick = {
                    navController.navigate(route = item.route) {
                        popUpTo(HomeRoutes.HOME) {
                            if (backStackEntry.value?.destination?.route == HomeRoutes.HOME) {
                                UserManager.loggedUser = null
                            }
                        }
                    }
                },
                icon = { Icon(imageVector = item.icon, contentDescription = "${item.name} Icon") },
                label = { Text(text = item.name) }
            )
        }
    }
}
