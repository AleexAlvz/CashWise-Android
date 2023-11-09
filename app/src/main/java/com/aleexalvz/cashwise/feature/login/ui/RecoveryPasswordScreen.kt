package com.aleexalvz.cashwise.feature.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aleexalvz.cashwise.ui.theme.GrayDefault

@Composable
fun RecoveryPasswordScreen(

) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayDefault)
    ) {

//        Image(
//            modifier = Modifier
//                .fillMaxHeight(0.5f)
//                .fillMaxWidth(0.4f),
//            contentScale = ContentScale.FillBounds,
//            painter = painterResource(id = R.drawable.left_decoration_recovery_password),
//            contentDescription = ""
//        )
    }
}

@Composable
@Preview
fun RecoveryPasswordScreenPreview() {
    RecoveryPasswordScreen()
}