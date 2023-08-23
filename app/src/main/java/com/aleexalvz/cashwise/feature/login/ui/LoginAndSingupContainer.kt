package com.aleexalvz.cashwise.feature.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aleexalvz.cashwise.R
import com.aleexalvz.cashwise.components.FirstIndex
import com.aleexalvz.cashwise.components.SecondIndex
import com.aleexalvz.cashwise.components.switchLoginButton
import com.aleexalvz.cashwise.feature.login.viewmodel.LoginViewModel
import com.aleexalvz.cashwise.feature.login.viewmodel.SignUpViewModel
import com.aleexalvz.cashwise.ui.theme.DarkBackground
import com.aleexalvz.cashwise.ui.theme.GrayDefault

const val LOGIN_SCREEN_NAME = "login"
const val SIGNUP_SCREEN_NAME = "signup"

@Composable
fun loginAndSignupScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel,
    signUpViewModel: SignUpViewModel,
    onLoginSuccessful: ()->Unit
) {

    val screenState = remember { mutableStateOf(LOGIN_SCREEN_NAME) }
    val indexSelectedState = remember {
        val index = if (screenState.value == LOGIN_SCREEN_NAME) FirstIndex
        else SecondIndex
        mutableStateOf(index)
    }

    Column(
        modifier = modifier
            .background(DarkBackground)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        switchLoginButton(firstButtonText = LOGIN_SCREEN_NAME,
            secondButtonText = SIGNUP_SCREEN_NAME,
            size = (320.dp to 44.dp),
            modifier = Modifier.padding(top = 93.dp),
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
            fontSize = 42.sp,
            color = Color.White
        )
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = message,
            fontSize = 20.sp,
            color = Color.White
        )

        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 56.dp)
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
                if (indexSelectedState.value == FirstIndex) {
                    loginContent(
                        modifier = Modifier.padding(26.dp),
                        loginViewModel = loginViewModel,
                        onLoginSuccessful = onLoginSuccessful
                    )
                } else {
                    signupContent(
                        modifier = Modifier.padding(26.dp),
                        signUpViewModel = signUpViewModel,
                        onLoginSuccessful = onLoginSuccessful
                    )
                }
            }
        }
    }
}