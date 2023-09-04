package com.aleexalvz.cashwise.feature.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import com.aleexalvz.cashwise.feature.home.ui.homeNavGraph
import com.aleexalvz.cashwise.ui.theme.CashWiseTheme
import com.aleexalvz.cashwise.ui.theme.DarkBackground

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CashWiseTheme {
                homeNavGraph()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topAppBar() {
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
}