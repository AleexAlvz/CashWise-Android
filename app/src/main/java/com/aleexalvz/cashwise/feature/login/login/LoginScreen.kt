package com.aleexalvz.cashwise.feature.login.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aleexalvz.cashwise.R
import com.aleexalvz.cashwise.ui.theme.DarkBackground
import com.aleexalvz.cashwise.ui.theme.GrayDefault
import com.aleexalvz.cashwise.ui.theme.Green

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccessful: () -> Unit = {},
    onGoToSignUp: () -> Unit = {}
) {
    val loginUIState by loginViewModel.uiState.collectAsStateWithLifecycle()

    LoginScreen(
        modifier = modifier,
        loginUIState = loginUIState,
        onLoginUIAction = loginViewModel::onUIAction,
        doLogin = loginViewModel::doLogin,
        onLoginSuccessful = onLoginSuccessful,
        onGoToSignUp = onGoToSignUp
    )
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginUIState: LoginUIState,
    onLoginUIAction: (LoginUIAction) -> Unit = {},
    doLogin: () -> Unit = {},
    onLoginSuccessful: () -> Unit = {},
    onGoToSignUp: () -> Unit = {}
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground), //TODO remove background setting by default on theme
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.padding(top = 64.dp),
            text = stringResource(R.string.login_screen_title),
            fontSize = 30.sp,
            color = Color.White
        )
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(R.string.login_screen_message),
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
                LoginContent(
                    modifier = Modifier
                        .padding(26.dp),
                    uiState = loginUIState,
                    onLoginSuccessful = onLoginSuccessful,
                    onUIAction = onLoginUIAction,
                    doLogin = doLogin
                )

                val registerText = buildAnnotatedString {
                    append("Don't have an account yet?")
                    withStyle(
                        SpanStyle(
                            color = Green
                        )
                    ){
                        append("\nRegister now")
                    }
                }

                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .clickable { onGoToSignUp() },
                    text = registerText,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        Modifier.fillMaxSize(),
        loginUIState = LoginUIState(
            email = "sample@sample.com",
            password = "6516156",
            rememberMe = true,
            emailError = null,
            passwordError = null,
            loginState = false
        )
    )
}