package com.aleexalvz.cashwise.feature.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aleexalvz.cashwise.feature.home.ui.HomeNavGraph
import com.aleexalvz.cashwise.ui.theme.CashWiseTheme

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CashWiseTheme {
                HomeNavGraph()
            }
        }
    }
}
