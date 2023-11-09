package com.aleexalvz.cashwise.feature.home.ui

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    titleState: MutableState<String>
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = titleState.value,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.White
            )
        }, colors = TopAppBarDefaults.mediumTopAppBarColors()
    )
}


