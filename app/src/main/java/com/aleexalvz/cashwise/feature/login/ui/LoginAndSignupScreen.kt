package com.aleexalvz.cashwise.feature.login.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aleexalvz.cashwise.R
import com.aleexalvz.cashwise.components.FirstIndex
import com.aleexalvz.cashwise.components.SecondIndex
import com.aleexalvz.cashwise.components.SwitchLoginButton
import com.aleexalvz.cashwise.feature.login.data.AuthState
import com.aleexalvz.cashwise.feature.login.data.LoginUIAction
import com.aleexalvz.cashwise.feature.login.data.LoginUIState
import com.aleexalvz.cashwise.feature.login.data.SignUpUIAction
import com.aleexalvz.cashwise.feature.login.data.SignUpUIState
import com.aleexalvz.cashwise.feature.login.viewmodel.LoginViewModel
import com.aleexalvz.cashwise.feature.login.viewmodel.SignUpViewModel
import com.aleexalvz.cashwise.helper.ObserveAsEvents
import com.aleexalvz.cashwise.ui.theme.DarkBackground
import com.aleexalvz.cashwise.ui.theme.GrayDefault
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

const val LOGIN_SCREEN_NAME = "login"
const val SIGNUP_SCREEN_NAME = "signup"

@Composable
fun LoginAndSignupScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel(),
    signUpViewModel: SignUpViewModel = hiltViewModel(),
    onLoginSuccessful: () -> Unit = {}
) {
    val loginUIState by loginViewModel.uiState.collectAsStateWithLifecycle()
    val signupUIState by signUpViewModel.uiState.collectAsStateWithLifecycle()

    LoginAndSignupScreen(
        modifier = modifier,
        loginUIState = loginUIState,
        signupUIState = signupUIState,
        onLoginUIAction = loginViewModel::onUIAction,
        doLogin = loginViewModel::doLogin,
        loginUIEvent = loginViewModel.uiEvents,
        onSignUpUIAction = signUpViewModel::onUIAction,
        doSignup = signUpViewModel::doSignup,
        signupUIEvent = signUpViewModel.uiEvents,
        onLoginSuccessful = onLoginSuccessful
    )
}

@Composable
fun LoginAndSignupScreen(
    modifier: Modifier = Modifier,
    loginUIState: LoginUIState,
    signupUIState: SignUpUIState,
    onLoginUIAction: (LoginUIAction) -> Unit = {},
    doLogin: () -> Unit = {},
    loginUIEvent: SharedFlow<AuthState>,
    onSignUpUIAction: (SignUpUIAction) -> Unit = {},
    doSignup: () -> Unit = {},
    signupUIEvent: SharedFlow<AuthState>,
    onLoginSuccessful: () -> Unit = {}
) {
    val context = LocalContext.current

    val screenState = remember { mutableStateOf(LOGIN_SCREEN_NAME) }

    val indexSelectedState = remember {
        val index = if (screenState.value == LOGIN_SCREEN_NAME) FirstIndex
        else SecondIndex
        mutableIntStateOf(index)
    }

    fun handleEvents(authState: AuthState) {
        when (authState) {
            is AuthState.OnSuccess -> onLoginSuccessful()

            is AuthState.OnFailure -> {
                Toast.makeText(context, authState.throwable.message, Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    ObserveAsEvents(flow = signupUIEvent, onEvent = ::handleEvents)
    ObserveAsEvents(flow = loginUIEvent, onEvent = ::handleEvents)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground), //TODO remove background setting by default on theme
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SwitchLoginButton(firstButtonText = LOGIN_SCREEN_NAME,
            secondButtonText = SIGNUP_SCREEN_NAME,
            size = (320.dp to 44.dp),
            modifier = Modifier.padding(top = 32.dp),
            indexSelected = indexSelectedState,
            onClickListener = {
                if (screenState.value != it) screenState.value = it
            })

        val (text, message) = if (screenState.value == LOGIN_SCREEN_NAME) {
            stringResource(R.string.login_screen_title) to stringResource(R.string.login_screen_message)
        } else {
            stringResource(R.string.singup_screen_title) to stringResource(R.string.signup_screen_message)
        }

        Text(
            modifier = Modifier.padding(top = 64.dp),
            text = text,
            fontSize = 36.sp,
            color = Color.White
        )
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = message,
            fontSize = 18.sp,
            color = Color.White
        )

        Box(
            Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 56.dp)
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                Card(
                    colors = CardColors(
                        containerColor = GrayDefault,
                        contentColor = Color.White,
                        disabledContainerColor = GrayDefault,
                        disabledContentColor = Color.White
                    ),
                    modifier = Modifier.fillMaxSize(),
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                ) {
                    if (indexSelectedState.intValue == FirstIndex) {
                        LoginContent(
                            modifier = Modifier.padding(26.dp),
                            uiState = loginUIState,
                            onUIAction = onLoginUIAction,
                            doLogin = doLogin
                        )
                    } else {
                        SignupContent(
                            modifier = Modifier.padding(26.dp),
                            uiState = signupUIState,
                            onUIAction = onSignUpUIAction,
                            doSignup = doSignup
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginAndSignupScreenPreview() {
    LoginAndSignupScreen(
        Modifier.fillMaxSize(),
        loginUIState = LoginUIState(
            email = "sample@sample.com",
            password = "6516156",
            rememberMe = true,
            emailError = null,
            passwordError = null,
        ),
        loginUIEvent = MutableSharedFlow(),
        signupUIState = SignUpUIState(
            email = "sample@sample.com",
            password = "6516156",
            confirmPassword = "6516156",
            emailError = null,
            passwordError = null,
            confirmPasswordError = null,
        ),
        signupUIEvent = MutableSharedFlow()
    )
}