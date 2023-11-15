package com.aleexalvz.cashwise.feature.login.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aleexalvz.cashwise.components.GradientButton
import com.aleexalvz.cashwise.components.textfield.DefaultOutlinedTextField
import com.aleexalvz.cashwise.components.textfield.PasswordOutlinedTextField
import com.aleexalvz.cashwise.ui.theme.GradGreenButton1
import com.aleexalvz.cashwise.ui.theme.GradGreenButton2
import com.aleexalvz.cashwise.ui.theme.GradGreenButton3

@Composable
fun SignupContent(
    modifier: Modifier,
    uiState: SignUpUIState,
    onLoginSuccessful: () -> Unit = {},
    onUIAction: (SignUpUIAction) -> Unit = {},
    doSignup: () -> Unit = {}
) {

    if (uiState.signUpState) onLoginSuccessful()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        DefaultOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            text = uiState.email,
            onValueChange = { onUIAction(SignUpUIAction.UpdateEmail(it)) },
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
            onValueChange = { onUIAction(SignUpUIAction.UpdatePassword(it)) },
            labelText = "Password",
            contentDescription = "Password field",
            errorMessage = uiState.passwordError,
        )

        PasswordOutlinedTextField(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            text = uiState.confirmPassword,
            onValueChange = { onUIAction(SignUpUIAction.UpdateConfirmPassword(it)) },
            labelText = "Confirm your password",
            contentDescription = "Password confirmation field",
            errorMessage = uiState.confirmPasswordError,
        )

        GradientButton(
            modifier = Modifier
                .padding(top = 20.dp)
                .width(310.dp)
                .height(50.dp),
            onClickListener = doSignup,
            text = "Sign Up",
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
fun SignupContentPreview() {
    SignupContent(
        modifier = Modifier
            .wrapContentSize()
            .padding(start = 20.dp, end = 20.dp),
        uiState = SignUpUIState(
            email = "sample@sample.com",
            password = "6516156",
            confirmPassword = "6516156",
            emailError = null,
            passwordError = null,
            confirmPasswordError = null,
            signUpState = false
        )
    )
}
