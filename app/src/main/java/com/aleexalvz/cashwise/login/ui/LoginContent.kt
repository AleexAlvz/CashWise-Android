package com.aleexalvz.cashwise.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.aleexalvz.cashwise.ui.theme.GrayDefault
import com.aleexalvz.cashwise.ui.theme.Green

@Composable
fun loginContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val email = rememberSaveable { mutableStateOf("") }
        loginTextFieldWithValidation(
            field = email,
            labelText = "Email",
            leadingIconImageVector = Icons.Default.Email,
            contentDescription = "Email field"
        )
    }
}

@Composable
fun loginTextFieldWithValidation(
    field: MutableState<String>,
    labelText: String,
    leadingIconImageVector: ImageVector,
    contentDescription: String
) {

    val outlinedTextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = Green,
        unfocusedBorderColor = Green,
        cursorColor = Color.White,
        focusedTextColor = Color.White,
    )
    OutlinedTextField(
        value = field.value,
        onValueChange = { field.value = it },
        singleLine = true,
        label = {
            Text(
                text = labelText,
                color = Green
            )
        },
        leadingIcon = {
            Icon(
                leadingIconImageVector,
                contentDescription = contentDescription
            )
        },
        colors = outlinedTextFieldColors
    )
}

@Composable
@Preview
fun loginContentPreview() {
    loginContent(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayDefault)
    )
}