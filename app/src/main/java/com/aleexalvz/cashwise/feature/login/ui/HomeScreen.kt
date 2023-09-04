package com.aleexalvz.cashwise.feature.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.aleexalvz.cashwise.ui.theme.DarkBackground

@Composable
fun homeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Home",
            color = Color.White,
            fontSize = 32.sp
        )
    }
}

@Composable
@Preview
fun homeScreenPreview() {
    homeScreen()
}