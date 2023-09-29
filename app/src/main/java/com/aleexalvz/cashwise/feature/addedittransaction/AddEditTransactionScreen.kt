package com.aleexalvz.cashwise.feature.addedittransaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.aleexalvz.cashwise.components.OutlinedTextFieldWithValidation
import com.aleexalvz.cashwise.ui.theme.DarkBackground

@Composable
fun AddEditTransactionScreen(
    transactionId: Long? = null
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
//        OutlinedTextFieldWithValidation(
//            text = "",
//            onValueChange = {},
//            labelText = "",
//            leadingIconImageVector = ,
//            contentDescription =
//        )
    }
}

@Preview
@Composable
fun AddEditTransactionScreenPreview() {
    AddEditTransactionScreen(0)
}