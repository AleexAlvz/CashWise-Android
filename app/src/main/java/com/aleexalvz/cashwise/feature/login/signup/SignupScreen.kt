package com.aleexalvz.cashwise.feature.login.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aleexalvz.cashwise.R
import com.aleexalvz.cashwise.ui.theme.DarkBackground
import com.aleexalvz.cashwise.ui.theme.GrayDefault

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    singUpViewModel: SignUpViewModel = hiltViewModel(),
    onSignUpSuccessful: () -> Unit = {}
) {
    val loginUIState by singUpViewModel.uiState.collectAsStateWithLifecycle()

    SignupScreen(
        modifier = modifier,
        signUpUIState = loginUIState,
        onSignUpUIAction = singUpViewModel::onUIAction,
        doSignUp = singUpViewModel::doSignup,
        onLoginSuccessful = onSignUpSuccessful
    )
}

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    signUpUIState: SignUpUIState,
    onSignUpUIAction: (SignUpUIAction) -> Unit = {},
    doSignUp: () -> Unit = {},
    onLoginSuccessful: () -> Unit = {}
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground), //TODO remove background setting by default on theme
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.padding(top = 64.dp),
            text = stringResource(R.string.singup_screen_title),
            fontSize = 30.sp,
            color = Color.White
        )
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(R.string.signup_screen_message),
            fontSize = 14.sp,
            color = Color.White
        )

        Box(
            Modifier.fillMaxSize()
        ) {
            Card(
                modifier = Modifier
                    .padding(top = 56.dp)
                    .fillMaxSize(),
                colors = CardColors(
                    containerColor = GrayDefault,
                    contentColor = Color.White,
                    disabledContainerColor = GrayDefault,
                    disabledContentColor = Color.White
                ),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
            ) {
                SignupContent(
                    modifier = Modifier
                        .padding(26.dp),
                    uiState = signUpUIState,
                    onLoginSuccessful = onLoginSuccessful,
                    onUIAction = onSignUpUIAction,
                    doSignup = doSignUp
                )
            }
        }
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    SignupScreen(
        Modifier.fillMaxSize(),
        signUpUIState = SignUpUIState(
            email = "sample@sample.com",
            password = "6516156",
            confirmPassword = "6516156",
            emailError = null,
            passwordError = null,
            confirmPasswordError = null
        )
    )
}