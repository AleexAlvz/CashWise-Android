package com.aleexalvz.cashwise.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aleexalvz.cashwise.login.ui.LoginNavGraph
import com.aleexalvz.cashwise.login.viewmodel.LoginViewModel
import com.aleexalvz.cashwise.ui.theme.CashWiseTheme

class LoginActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels { LoginViewModel.Factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CashWiseTheme {
                LoginNavGraph(loginViewModel)
            }
        }
    }
}