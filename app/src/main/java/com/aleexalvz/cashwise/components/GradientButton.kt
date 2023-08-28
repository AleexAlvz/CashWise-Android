package com.aleexalvz.cashwise.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aleexalvz.cashwise.ui.theme.GradGreenButton1
import com.aleexalvz.cashwise.ui.theme.GradGreenButton2
import com.aleexalvz.cashwise.ui.theme.GradGreenButton3

@Composable
fun gradientButton(
    modifier: Modifier = Modifier,
    onClickListener: () -> Unit,
    text: String,
    brush: Brush
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
        ),
        contentPadding = PaddingValues(),
        onClick = { onClickListener() },
        shape = RoundedCornerShape(200.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = Color.White,
                fontSize = 22.sp,
                modifier = Modifier.wrapContentSize(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
@Preview
fun gradientButtonPreview() {
    gradientButton(
        text = "Click me",
        onClickListener = {},
        modifier = Modifier
            .padding(10.dp)
            .width(232.dp)
            .height(50.dp),
        brush = Brush.verticalGradient(
            listOf(
                GradGreenButton1, GradGreenButton2, GradGreenButton3
            )
        )
    )
}