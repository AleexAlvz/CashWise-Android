package com.aleexalvz.cashwise.feature.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aleexalvz.cashwise.feature.home.HomeActivity
import com.aleexalvz.cashwise.feature.login.ui.LoginNavGraph
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

    override fun onResume() {
        super.onResume()
        UserManager.loggedUser = null
    }

    private fun onLoginSuccessful() {
        UserManager.loggedUser?.email?.let {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK + Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}