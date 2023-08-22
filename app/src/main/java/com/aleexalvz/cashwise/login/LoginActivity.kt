package com.aleexalvz.cashwise.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.aleexalvz.cashwise.data.mocked.MockedLoginRepository
import com.aleexalvz.cashwise.ui.theme.CashWiseTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MockedLoginRepository().getAllUsers(this)
        setContent {
            CashWiseTheme {
                LoginNavGraph()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CashWiseTheme {
        Greeting("Android")
    }
}