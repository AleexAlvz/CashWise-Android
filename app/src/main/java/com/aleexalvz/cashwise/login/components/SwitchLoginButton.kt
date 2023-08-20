package com.aleexalvz.cashwise.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aleexalvz.cashwise.login.LoginRoutes
import com.aleexalvz.cashwise.ui.theme.GrayDefault
import com.aleexalvz.cashwise.ui.theme.Green
import java.util.*

@Composable
fun switchLoginButton(
    route: String,
    onClickListener: ((String) -> Unit)
) {
    val isLoginSelected = route == LoginRoutes.LOGIN
    Card(
        modifier = Modifier
            .width(360.dp)
            .height(44.dp)
            .background(color = GrayDefault)
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(2.dp)
        ) {
            switchButton(
                isSelected = isLoginSelected,
                route = LoginRoutes.LOGIN,
                onClickListener = onClickListener)
            switchButton(
                isSelected = !isLoginSelected,
                route = LoginRoutes.SIGNUP,
                onClickListener = onClickListener)
        }
    }
}

@Composable
fun switchButton(
    isSelected: Boolean,
    route: String,
    onClickListener: (String) -> Unit
) {
    val backgroundColor = if (isSelected) Green else GrayDefault

    val shape = if (route == LoginRoutes.LOGIN) {
        RoundedCornerShape(
            topStart = 16.dp,
            bottomStart = 16.dp
        )
    } else {
        RoundedCornerShape(
            bottomEnd = 16.dp,
            topEnd = 16.dp
        )
    }

    Button(
        modifier = Modifier
            .width(180.dp)
            .background(backgroundColor),
        onClick = { onClickListener(route) },
        shape = shape
    ) {
        Text(text = route.lowercase(Locale.ROOT))
    }
}

@Preview
@Composable
fun switchLoginButtonPreview() {
    switchLoginButton(
        route = LoginRoutes.LOGIN,
        onClickListener = {}
    )
}