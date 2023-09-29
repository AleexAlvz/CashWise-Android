package com.aleexalvz.cashwise.feature.login.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aleexalvz.cashwise.components.CheckBox
import com.aleexalvz.cashwise.components.GradientButton
import com.aleexalvz.cashwise.components.OutlinedTextFieldWithValidation
import com.aleexalvz.cashwise.feature.login.viewmodel.LoginViewModel
import com.aleexalvz.cashwise.ui.theme.GradGreenButton1
import com.aleexalvz.cashwise.ui.theme.GradGreenButton2
import com.aleexalvz.cashwise.ui.theme.GradGreenButton3
import com.aleexalvz.cashwise.ui.theme.Green

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccessful: () -> Unit
) {

    val uiState = loginViewModel.uiState.collectAsState()

    if (uiState.value.loginState) onLoginSuccessful()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextFieldWithValidation(
            modifier = Modifier.fillMaxWidth(),
            text = uiState.value.email,
            onValueChange = loginViewModel::updateEmail,
            labelText = "Email",
            leadingIconImageVector = Icons.Default.Email,
            contentDescription = "Email field",
            errorMessage = uiState.value.emailError,
        )

        OutlinedTextFieldWithValidation(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            text = uiState.value.password,
            onValueChange = { loginViewModel.updatePassword(it) },
            labelText = "Password",
            leadingIconImageVector = Icons.Default.Lock,
            contentDescription = "Password field",
            isPassword = true,
            errorMessage = uiState.value.passwordError,
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, top = 4.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            CheckBox(
                selected = uiState.value.rememberMe,
                onStateChanged = {
                    loginViewModel.updateRememberMeCheckBox(it)
                },
                text = "Remember me",
            )
        }

        //TODO Add disabled state
        GradientButton(
            modifier = Modifier
                .padding(top = 20.dp)
                .width(310.dp)
                .height(50.dp),
            onClickListener = loginViewModel::doLogin,
            text = "Login",
            brush = Brush.verticalGradient(
                listOf(
                    GradGreenButton1, GradGreenButton2, GradGreenButton3
                )
            )
        )

        Row(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 32.dp, bottom = 16.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                modifier = Modifier
                    .clickable {
                        //Click to recovery password
                    },
                text = "Forgot your password?",
                fontSize = 16.sp,
                color = Green
            )
        }
    }
}
