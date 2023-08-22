package com.aleexalvz.cashwise.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aleexalvz.cashwise.components.checkBox
import com.aleexalvz.cashwise.components.outlinedTextFieldWithValidation
import com.aleexalvz.cashwise.ui.theme.GrayDefault

@Composable
fun loginContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val emailState = rememberSaveable { mutableStateOf("") }
        outlinedTextFieldWithValidation(
            modifier = Modifier.fillMaxWidth(),
            field = emailState,
            labelText = "Email",
            leadingIconImageVector = Icons.Default.Email,
            contentDescription = "Email field"
        )

        val passwordState = rememberSaveable { mutableStateOf("") }
        outlinedTextFieldWithValidation(
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth(),
            field = passwordState,
            labelText = "Password",
            leadingIconImageVector = Icons.Default.Lock,
            contentDescription = "Password field",
            isPassword = true
        )
        Row(
            Modifier.fillMaxWidth().padding(start = 4.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            val rememberMeState = rememberSaveable { mutableStateOf(false) }
            checkBox(
                selected = rememberMeState,
                text = "Remember me"
            )
        }
    }
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