package com.aleexalvz.cashwise.feature.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.aleexalvz.cashwise.R
import com.aleexalvz.cashwise.ui.theme.GrayDefault

@Composable
fun recoveryPasswordScreen(

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
fun recoveryPasswordScreenPreview() {
    recoveryPasswordScreen()
}