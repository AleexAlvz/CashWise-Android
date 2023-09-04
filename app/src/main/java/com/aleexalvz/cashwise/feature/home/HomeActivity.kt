package com.aleexalvz.cashwise.feature.home

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import com.aleexalvz.cashwise.feature.home.ui.homeNavGraph
import com.aleexalvz.cashwise.ui.theme.CashWiseTheme
import com.aleexalvz.cashwise.ui.theme.DarkBackground

class HomeActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContent {
            CashWiseTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    text = "Cash Wise",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    color = Color.White
                                )
                            }, colors = TopAppBarDefaults.mediumTopAppBarColors(
                                containerColor = DarkBackground
                            )
                        )
                    },
                    content = { homeNavGraph() }
                )
            }
        }
    }
}