package com.aleexalvz.cashwise.feature.login.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.aleexalvz.cashwise.components.GradientButton
import com.aleexalvz.cashwise.components.OutlinedTextFieldWithValidation
import com.aleexalvz.cashwise.feature.login.viewmodel.SignUpViewModel
import com.aleexalvz.cashwise.ui.theme.GradGreenButton1
import com.aleexalvz.cashwise.ui.theme.GradGreenButton2
import com.aleexalvz.cashwise.ui.theme.GradGreenButton3

@Composable
fun SignupContent(
    modifier: Modifier,
    signUpViewModel: SignUpViewModel,
    onLoginSuccessful: () -> Unit
) {

    val uiState = signUpViewModel.uiState.collectAsState()

    if (uiState.value.signUpState) onLoginSuccessful()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextFieldWithValidation(
            modifier = Modifier.fillMaxWidth(),
            text = uiState.value.email,
            onValueChange = signUpViewModel::updateEmail,
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
            onValueChange = signUpViewModel::updatePassword,
            labelText = "Password",
            leadingIconImageVector = Icons.Default.Lock,
            contentDescription = "Password field",
            isPassword = true,
            errorMessage = uiState.value.passwordError,
        )

        OutlinedTextFieldWithValidation(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            text = uiState.value.confirmPassword,
            onValueChange = signUpViewModel::updateConfirmPassword,
            labelText = "Confirm your password",
            leadingIconImageVector = Icons.Default.Lock,
            contentDescription = "Password confirmation field",
            isPassword = true,
            errorMessage = uiState.value.confirmPasswordError,
        )

        //TODO Add disabled state
        GradientButton(
            modifier = Modifier
                .padding(top = 20.dp)
                .width(310.dp)
                .height(50.dp),
            onClickListener = signUpViewModel::signupUser,
            text = "Sign Up",
            brush = Brush.verticalGradient(
                listOf(
                    GradGreenButton1, GradGreenButton2, GradGreenButton3
                )
            )
        )
    }
}
