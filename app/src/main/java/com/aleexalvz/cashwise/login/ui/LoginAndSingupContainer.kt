package com.aleexalvz.cashwise.login.ui

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aleexalvz.cashwise.R
import com.aleexalvz.cashwise.components.FirstIndex
import com.aleexalvz.cashwise.components.SecondIndex
import com.aleexalvz.cashwise.components.switchLoginButton
import com.aleexalvz.cashwise.login.LoginRoutes
import com.aleexalvz.cashwise.ui.theme.DarkBackground
import com.aleexalvz.cashwise.ui.theme.GrayDefault

@Composable
fun loginAndSignupScreen(
    modifier: Modifier = Modifier,
    route: String,
) {

    val routeState = remember { mutableStateOf(route) }
    val indexSelectedState = remember {
        val index =
            if (routeState.value == LoginRoutes.LOGIN) FirstIndex
            else SecondIndex
        mutableStateOf(index)
    }

    Column(
        modifier = modifier
            .background(DarkBackground)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        switchLoginButton(
            firstButtonText = LoginRoutes.LOGIN,
            secondButtonText = LoginRoutes.SIGNUP,
            size = (320.dp to 44.dp),
            modifier = Modifier
                .padding(top = 93.dp),
            indexSelected = indexSelectedState,
            onClickListener = {
                if (routeState.value != it) routeState.value = it
            }
        )

        val (text, message) = if (routeState.value == LoginRoutes.LOGIN) {
            stringResource(R.string.login_screen_title) to
                    stringResource(R.string.login_screen_message)
        } else {
            stringResource(R.string.singup_screen_title) to
                    stringResource(R.string.signup_screen_message)
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
                modifier = Modifier
                    .fillMaxSize(),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
            ) {
                if (indexSelectedState.value == FirstIndex){
                    loginContent(
                        modifier = Modifier.padding(26.dp)
                    )
                } else {
                    signupContent(
                        modifier = Modifier.padding(26.dp)
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun loginAndSignupScreenPreview() {
    loginAndSignupScreen(
        route = LoginRoutes.LOGIN
    )
}