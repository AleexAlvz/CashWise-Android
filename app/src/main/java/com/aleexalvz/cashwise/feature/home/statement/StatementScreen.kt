package com.aleexalvz.cashwise.feature.home.statement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.aleexalvz.cashwise.ui.theme.DarkBackground
import com.aleexalvz.cashwise.ui.theme.Green

@Composable
fun StatementScreen() {
    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { /*TODO*/ },
                containerColor = Green,
                contentColor = Color.White,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add icon"
                )
                Text(text = "New transaction")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBackground),
            contentAlignment = Alignment.TopCenter
        ) {

        }
    }
}

@Composable
@Preview
fun StatementScreenPreview() {
    StatementScreen()
}