package com.aleexalvz.cashwise.feature.addedittransaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aleexalvz.cashwise.components.textfield.DefaultOutlinedTextField
import com.aleexalvz.cashwise.components.textfield.TextFieldWithDropDown
import com.aleexalvz.cashwise.data.model.transaction.TransactionCategory
import com.aleexalvz.cashwise.data.model.transaction.TransactionType
import com.aleexalvz.cashwise.data.model.transaction.getTransactionCategoryByName
import com.aleexalvz.cashwise.data.model.transaction.getTransactionTypeByName
import com.aleexalvz.cashwise.ui.theme.DarkBackground

@Composable
fun AddEditTransactionScreen(
    modifier: Modifier,
    transactionId: Long? = null,
    viewModel: TransactionViewModel = hiltViewModel()
) {

    val uiState = viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        DefaultOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            text = uiState.value.title,
            onValueChange = viewModel::updateTitle,
            labelText = "Title"
        )

        TextFieldWithDropDown(
            modifier = Modifier.fillMaxWidth(),
            dropDownValues = TransactionCategory.values().map { it.name },
            "Category",
            onSelectedItem = { selectedCategory ->
                uiState.value.category = getTransactionCategoryByName(selectedCategory)
            }
        )

        TextFieldWithDropDown(
            modifier = Modifier.fillMaxWidth(),
            dropDownValues = TransactionType.values().map { it.name },
            "Type",
            onSelectedItem = { selectedType ->
                uiState.value.type = getTransactionTypeByName(selectedType)
            }
        )
    }
}

@Preview
@Composable
fun AddEditTransactionScreenPreview() {
    AddEditTransactionScreen(
        Modifier.padding(26.dp)
    )
}