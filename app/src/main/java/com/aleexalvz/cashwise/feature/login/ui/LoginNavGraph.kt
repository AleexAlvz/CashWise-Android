package com.aleexalvz.cashwise.feature.login.ui

import androidx.compose.animation.EnterTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aleexalvz.cashwise.feature.login.LoginRoutes
import com.aleexalvz.cashwise.feature.login.login.LoginScreen
import com.aleexalvz.cashwise.feature.login.signup.SignUpScreen

@Composable
fun LoginNavGraph(
    onLoginSuccessful: () -> Unit
) {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LoginRoutes.LOGIN
    ) {
        composable(
            route = LoginRoutes.LOGIN
        ) {
            LoginScreen(
                onLoginSuccessful = onLoginSuccessful,
                onGoToSignUp = { navController.navigate(LoginRoutes.SIGN_UP) }
            )
        }
        composable(
            route = LoginRoutes.SIGN_UP
        ) {
            SignUpScreen(
                onSignUpSuccessful = onLoginSuccessful
            )
        }
    }
}
