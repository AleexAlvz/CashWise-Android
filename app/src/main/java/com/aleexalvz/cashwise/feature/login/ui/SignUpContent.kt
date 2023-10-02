package com.aleexalvz.cashwise.feature.login.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aleexalvz.cashwise.components.GradientButton
import com.aleexalvz.cashwise.components.textfield.DefaultOutlinedTextField
import com.aleexalvz.cashwise.components.textfield.PasswordOutlinedTextField
import com.aleexalvz.cashwise.feature.login.viewmodel.SignUpViewModel
import com.aleexalvz.cashwise.ui.theme.GradGreenButton1
import com.aleexalvz.cashwise.ui.theme.GradGreenButton2
import com.aleexalvz.cashwise.ui.theme.GradGreenButton3

@Composable
fun SignupContent(
    modifier: Modifier,
    signUpViewModel: SignUpViewModel = hiltViewModel(),
    onLoginSuccessful: () -> Unit
) {

    val uiState = signUpViewModel.uiState.collectAsState()

    if (uiState.value.signUpState) onLoginSuccessful()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        DefaultOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            text = uiState.value.email,
            onValueChange = signUpViewModel::updateEmail,
            labelText = "Email",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email icon"
                )
            },
            errorMessage = uiState.value.emailError,
        )

        PasswordOutlinedTextField(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            text = uiState.value.password,
            onValueChange = signUpViewModel::updatePassword,
            labelText = "Password",
            contentDescription = "Password field",
            errorMessage = uiState.value.passwordError,
        )

        PasswordOutlinedTextField(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            text = uiState.value.confirmPassword,
            onValueChange = signUpViewModel::updateConfirmPassword,
            labelText = "Confirm your password",
            contentDescription = "Password confirmation field",
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
