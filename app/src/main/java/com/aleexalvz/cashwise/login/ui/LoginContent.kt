package com.aleexalvz.cashwise.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aleexalvz.cashwise.components.checkBox
import com.aleexalvz.cashwise.components.gradientButton
import com.aleexalvz.cashwise.components.outlinedTextFieldWithValidation
import com.aleexalvz.cashwise.ui.theme.*

@Composable
fun loginContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val emailState = rememberSaveable { mutableStateOf("") }
        outlinedTextFieldWithValidation(
            modifier = Modifier.fillMaxWidth(),
            field = emailState,
            labelText = "Email",
            leadingIconImageVector = Icons.Default.Email,
            contentDescription = "Email field"
        )

        val passwordState = rememberSaveable { mutableStateOf("") }
        outlinedTextFieldWithValidation(
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth(),
            field = passwordState,
            labelText = "Password",
            leadingIconImageVector = Icons.Default.Lock,
            contentDescription = "Password field",
            isPassword = true
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, top = 6.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            val rememberMeState = rememberSaveable { mutableStateOf(true) }
            checkBox(
                selected = rememberMeState,
                text = "Remember me"
            )
        }
        gradientButton(
            modifier = Modifier
                .padding(top = 20.dp)
                .width(310.dp)
                .height(50.dp),
            onClickListener = {/*TODO*/ },
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
                .padding(bottom = 32.dp, top = 80.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                modifier = Modifier
                    .clickable {
                        //Click to recovery password
                    },
                text = "Forgot your password?",
                fontSize = 22.sp,
                color = Green
            )
        }
    }
}

@Composable
@Preview
fun loginContentPreview() {
    loginContent(
        modifier = Modifier
            .wrapContentSize()
            .background(GrayDefault)
    )
}