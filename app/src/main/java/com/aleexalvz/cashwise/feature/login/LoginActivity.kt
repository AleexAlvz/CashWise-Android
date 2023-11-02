package com.aleexalvz.cashwise.feature.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.aleexalvz.cashwise.feature.home.HomeActivity
import com.aleexalvz.cashwise.feature.login.ui.LoginNavGraph
import com.aleexalvz.cashwise.feature.login.viewmodel.SignUpViewModel
import com.aleexalvz.cashwise.foundation.UserManager
import com.aleexalvz.cashwise.ui.theme.CashWiseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // TODO fix background theme color
            CashWiseTheme {
                LoginNavGraph(
                    onLoginSuccessful = ::onLoginSuccessful
                )
            }
        }
    }

    private fun onLoginSuccessful() {
        UserManager.loggedUser?.email?.let {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}