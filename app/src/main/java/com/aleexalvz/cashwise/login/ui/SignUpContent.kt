package com.aleexalvz.cashwise.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.aleexalvz.cashwise.components.outlinedTextFieldWithValidation
import com.aleexalvz.cashwise.ui.theme.GrayDefault

@Composable
fun signupContent(
    modifier: Modifier
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

        val confirmPasswordState = rememberSaveable { mutableStateOf("") }
        outlinedTextFieldWithValidation(
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth(),
            field = confirmPasswordState,
            labelText = "Confirm your password",
            leadingIconImageVector = Icons.Default.Lock,
            contentDescription = "Password confirmation field",
            isPassword = true
        )
    }
}

@Composable
@Preview
fun signupContentPreview() {
    signupContent(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayDefault)
    )
}
