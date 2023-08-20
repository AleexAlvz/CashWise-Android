package com.aleexalvz.cashwise.login.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.aleexalvz.cashwise.login.LoginRoutes

@Composable
fun loginModalDrawer(
    route: String,
    title: String,
    message: String
) {

    Column() {

    }

    when (route) {
        LoginRoutes.LOGIN -> {}//add top switch button to login
        LoginRoutes.SIGNUP -> {}//add top switch button to signup
        else -> {}
    }


}