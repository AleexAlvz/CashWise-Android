package com.aleexalvz.cashwise.feature.addedittransaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.aleexalvz.cashwise.components.OutlinedTextFieldWithValidation
import com.aleexalvz.cashwise.ui.theme.DarkBackground

@Composable
fun AddEditTransactionScreen(
    transactionId: Long? = null,
    viewModel: TransactionViewModel = hiltViewModel()
) {

    val uiState = viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        OutlinedTextFieldWithValidation(
            modifier = Modifier.fillMaxWidth(),
            text = uiState.value.title,
            onValueChange = viewModel::updateTitle,
            labelText = "Title",
            contentDescription = "Transaction Title Field"
        )
    }
}

@Preview
@Composable
fun AddEditTransactionScreenPreview() {
    AddEditTransactionScreen()
}