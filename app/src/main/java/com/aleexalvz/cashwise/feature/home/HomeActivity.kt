package com.aleexalvz.cashwise.feature.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aleexalvz.cashwise.ui.theme.CashWiseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
