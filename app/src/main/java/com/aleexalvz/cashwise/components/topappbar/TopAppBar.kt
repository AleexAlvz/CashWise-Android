package com.aleexalvz.cashwise.components.topappbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.aleexalvz.cashwise.ui.theme.GrayLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    uiState: TopAppBarUIState,
    onBackPressed: () -> Unit,
    onProfilePressed: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = uiState.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.White
            )
        },
        navigationIcon = {
            if (uiState.isBackButtonEnabled) {
                IconButton(onClick = { onBackPressed() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIosNew,
                        contentDescription = "back button",
                        tint = Color.White
                    )
                }
            }

            if (uiState.isProfileIconEnabled) {
                IconButton(
                    onClick = { onProfilePressed() },
                    colors = IconButtonColors(
                        containerColor = GrayLight,
                        contentColor = Color.White,
                        disabledContainerColor = GrayLight,
                        disabledContentColor = Color.White
                        )
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "profile button",
                        tint = Color.White
                    )
                }
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors()
    )
}

@Preview
@Composable
fun TopAppBarPreview() {
    TopAppBar(
        uiState = TopAppBarUIState(
            title = "title",
            isBackButtonEnabled = true,
            isProfileIconEnabled = true
        ),
        onBackPressed = {},
        onProfilePressed = {}
    )
}

