package com.aleexalvz.cashwise.login.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aleexalvz.cashwise.login.LoginRoutes
import com.aleexalvz.cashwise.ui.theme.GrayDefault
import com.aleexalvz.cashwise.ui.theme.GrayLight
import com.aleexalvz.cashwise.ui.theme.Green
import java.util.*

const val FirstIndex = 0
const val SecondIndex = 1

typealias Size = Pair<Dp, Dp>

@Composable
fun switchLoginButton(
    firstButtonText: String,
    secondButtonText: String,
    size: Size,
    modifier: Modifier = Modifier,
    indexSelected: MutableState<Int>,
    onClickListener: ((String) -> Unit)
) {
    Card(
        modifier = modifier
            .width(size.first)
            .height(size.second),
        shape = RoundedCornerShape(16.dp),
        colors = CardColors(
            containerColor = GrayLight,
            contentColor = Color.White,
            disabledContentColor = GrayLight,
            disabledContainerColor = Color.Gray
        )
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(2.dp)
        ) {
            val buttonWidth = (size.first / 2) - 2.dp
            switchButton(
                index = 0,
                size = (buttonWidth) to (size.second),
                indexSelected = indexSelected,
                title = firstButtonText,
                onClickListener = {
                    indexSelected.value = FirstIndex
                    onClickListener(it)
                }
            )
            switchButton(
                index = 1,
                size = (size.first - 2.dp) to (size.second),
                indexSelected = indexSelected,
                title = secondButtonText,
                onClickListener = {
                    indexSelected.value = SecondIndex
                    onClickListener(it)
                }
            )
        }
    }
}

@Composable
fun switchButton(
    index: Int,
    size: Size,
    indexSelected: MutableState<Int>,
    title: String,
    onClickListener: (String) -> Unit
) {
    val tintColor = if (indexSelected.value == index) Green else GrayDefault

    val shape = if (index == FirstIndex) {
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
            .width(size.first)
            .fillMaxHeight(),
        onClick = { onClickListener(title) },
        shape = shape,
        colors = ButtonColors(
            containerColor = tintColor,
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = title.lowercase(Locale.ROOT))
    }
}

@Preview
@Composable
fun switchLoginButtonPreview() {
    val indexSelected = remember {
        mutableStateOf(FirstIndex)
    }
    switchLoginButton(
        firstButtonText = LoginRoutes.LOGIN,
        secondButtonText = LoginRoutes.SIGNUP,
        size = 360.dp to 44.dp,
        indexSelected = indexSelected,
        onClickListener = {}
    )
}