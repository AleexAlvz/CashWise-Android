package com.aleexalvz.cashwise.feature.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Note
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
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
                onClick = { /*TODO*/ },
                icon = { Icon(imageVector = item.icon, contentDescription = "${item.name} Icon") },
                label = { Text(text = item.name) }
            )
        }
    }
}
