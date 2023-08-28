package com.aleexalvz.cashwise.feature.login.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aleexalvz.cashwise.feature.login.LoginRoutes
import com.aleexalvz.cashwise.feature.login.viewmodel.LoginViewModel
import com.aleexalvz.cashwise.feature.login.viewmodel.SignUpViewModel

@Composable
fun LoginNavGraph(
    loginViewModel: LoginViewModel,
    signUpViewModel: SignUpViewModel,
    onLoginSuccessful: ()->Unit
) {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LoginRoutes.LOGIN
    ) {
        composable(route = LoginRoutes.LOGIN) {
            loginAndSignupScreen(
                loginViewModel = loginViewModel,
                signUpViewModel = signUpViewModel,
                onLoginSuccessful = onLoginSuccessful
            )
        }
    }
}
