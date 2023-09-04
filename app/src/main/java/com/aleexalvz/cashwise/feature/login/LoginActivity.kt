package com.aleexalvz.cashwise.feature.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.aleexalvz.cashwise.feature.home.HomeActivity
import com.aleexalvz.cashwise.feature.login.ui.LoginNavGraph
import com.aleexalvz.cashwise.feature.login.viewmodel.LoginViewModel
import com.aleexalvz.cashwise.feature.login.viewmodel.SignUpViewModel
import com.aleexalvz.cashwise.foundation.UserManager
import com.aleexalvz.cashwise.ui.theme.CashWiseTheme

class LoginActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels { LoginViewModel.Factory }
    private val signUpViewModel: SignUpViewModel by viewModels { SignUpViewModel.Factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CashWiseTheme {
                LoginNavGraph(
                    loginViewModel,
                    signUpViewModel,
                    onLoginSuccessful = ::onLoginSuccessful
                )
            }
        }
    }

    private fun onLoginSuccessful() {
        val message = UserManager.loggedUser?.email?.let {
            startActivity(Intent(this, HomeActivity::class.java))
            "Loggin Successful: $it"
        } ?: "Failed: Not found logged user"
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_LONG
        ).show()
    }
}