package com.aleexalvz.cashwise.feature.login.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aleexalvz.cashwise.components.CheckBox
import com.aleexalvz.cashwise.components.GradientButton
import com.aleexalvz.cashwise.components.textfield.DefaultOutlinedTextField
import com.aleexalvz.cashwise.components.textfield.PasswordOutlinedTextField
import com.aleexalvz.cashwise.ui.theme.GradGreenButton1
import com.aleexalvz.cashwise.ui.theme.GradGreenButton2
import com.aleexalvz.cashwise.ui.theme.GradGreenButton3
import com.aleexalvz.cashwise.ui.theme.Green

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    uiState: LoginUIState,
    onLoginSuccessful: () -> Unit = {},
    onUIAction: (LoginUIAction) -> Unit = {},
    doLogin: () -> Unit = {}
) {

    if (uiState.loginState) onLoginSuccessful()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        DefaultOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            text = uiState.email,
            onValueChange = {
                onUIAction(LoginUIAction.UpdateEmail(it))
            },
            labelText = "Email",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email icon"
                )
            },
            errorMessage = uiState.emailError,
        )

        PasswordOutlinedTextField(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            text = uiState.password,
            onValueChange = { onUIAction(LoginUIAction.UpdatePassword(it)) },
            labelText = "Password",
            contentDescription = "Password field",
            errorMessage = uiState.passwordError,
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            CheckBox(
                modifier = Modifier.weight(1f),
                selected = uiState.rememberMe,
                onStateChanged = { onUIAction(LoginUIAction.UpdateRememberMeCheckBox(it)) },
                text = "Remember me",
                horizontalAlignment = Arrangement.Start
            )

            Text(
                modifier = Modifier
                    .padding(top = 2.dp)
                    .clickable {
                        //Click to recovery password
                    },
                text = "Forgot your password?",
                fontSize = 14.sp,
                color = Green
            )
        }



        GradientButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(top = 24.dp),
            onClickListener = doLogin,
            text = "Login",
            brush = Brush.verticalGradient(
                listOf(
                    GradGreenButton1, GradGreenButton2, GradGreenButton3
                )
            )
        )
    }
}

@Preview
@Composable
fun LoginContentPreview() {
    LoginContent(
        modifier = Modifier
            .wrapContentSize()
            .padding(start = 20.dp, end = 20.dp),
        uiState = LoginUIState(
            email = "sample@sample.com",
            password = "6516156",
            rememberMe = true,
            emailError = null,
            passwordError = null,
            loginState = false
        )
    )
}
