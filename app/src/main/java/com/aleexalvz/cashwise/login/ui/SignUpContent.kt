package com.aleexalvz.cashwise.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aleexalvz.cashwise.components.gradientButton
import com.aleexalvz.cashwise.components.outlinedTextFieldWithValidation
import com.aleexalvz.cashwise.ui.theme.GradGreenButton1
import com.aleexalvz.cashwise.ui.theme.GradGreenButton2
import com.aleexalvz.cashwise.ui.theme.GradGreenButton3
import com.aleexalvz.cashwise.ui.theme.GrayDefault

@Composable
fun signupContent(
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
    // TODO prepare viewModel to this Content, like loginViewModel

//        val emailState = rememberSaveable { mutableStateOf("") }
//        outlinedTextFieldWithValidation(
//            modifier = Modifier.fillMaxWidth(),
//            text = emailState,
//            labelText = "Email",
//            leadingIconImageVector = Icons.Default.Email,
//            contentDescription = "Email field"
//        )
//
//        val passwordState = rememberSaveable { mutableStateOf("") }
//        outlinedTextFieldWithValidation(
//            modifier = Modifier
//                .padding(top = 12.dp)
//                .fillMaxWidth(),
//            text = passwordState,
//            labelText = "Password",
//            leadingIconImageVector = Icons.Default.Lock,
//            contentDescription = "Password field",
//            isPassword = true
//        )
//
//        val confirmPasswordState = rememberSaveable { mutableStateOf("") }
//        outlinedTextFieldWithValidation(
//            modifier = Modifier
//                .padding(top = 12.dp)
//                .fillMaxWidth(),
//            text = confirmPasswordState,
//            labelText = "Confirm your password",
//            leadingIconImageVector = Icons.Default.Lock,
//            contentDescription = "Password confirmation field",
//            isPassword = true
//        )
//
//        gradientButton(
//            modifier = Modifier
//                .padding(top = 40.dp)
//                .width(310.dp)
//                .height(50.dp),
//            onClickListener = {/*TODO*/ },
//            text = "Sign Up",
//            brush = Brush.verticalGradient(
//                listOf(
//                    GradGreenButton1, GradGreenButton2, GradGreenButton3
//                )
//            )
//        )
    }
}

@Composable
@Preview
fun signupContentPreview() {
    signupContent(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayDefault)
    )
}
