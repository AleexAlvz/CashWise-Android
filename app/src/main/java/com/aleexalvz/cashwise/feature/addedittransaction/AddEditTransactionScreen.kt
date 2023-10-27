package com.aleexalvz.cashwise.feature.addedittransaction

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aleexalvz.cashwise.components.GradientButton
import com.aleexalvz.cashwise.components.textfield.DefaultOutlinedTextField
import com.aleexalvz.cashwise.components.textfield.TextFieldWithDatePicker
import com.aleexalvz.cashwise.components.textfield.TextFieldWithDropDown
import com.aleexalvz.cashwise.data.model.transaction.TransactionCategory
import com.aleexalvz.cashwise.data.model.transaction.TransactionType
import com.aleexalvz.cashwise.helper.toBrazilianDateFormat
import com.aleexalvz.cashwise.helper.toCurrencyString
import com.aleexalvz.cashwise.ui.theme.CashWiseTheme
import com.aleexalvz.cashwise.ui.theme.DarkBackground
import com.aleexalvz.cashwise.ui.theme.GradGreenButton1
import com.aleexalvz.cashwise.ui.theme.GradGreenButton2
import com.aleexalvz.cashwise.ui.theme.GradGreenButton3
import com.aleexalvz.cashwise.ui.theme.Green
import com.aleexalvz.cashwise.ui.theme.OutlinedGreen
import java.math.BigDecimal
import java.util.Date

@Composable
fun AddEditTransactionScreen(
    modifier: Modifier,
    transactionId: Long? = null,
    onFinish: () -> Unit,
    viewModel: TransactionViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AddEditTransactionScreen(
        modifier = modifier,
        transactionId = transactionId,
        uiState = uiState,
        updateTitle = viewModel::updateTitle,
        updateCategory = viewModel::updateCategory,
        updateType = viewModel::updateType,
        updateDate = viewModel::updateDate,
        updateAmount = viewModel::updateAmount,
        updateUnitValue = viewModel::updateUnitValue,
        addOrEditTransaction = viewModel::addOrEditTransaction,
        onFinish = onFinish
    )
}

@Composable
fun AddEditTransactionScreen(
    modifier: Modifier,
    uiState: AddEditTransactionUIState,
    updateTitle: (String) -> Unit,
    updateCategory: (String) -> Unit,
    updateType: (String) -> Unit,
    updateDate: (Long) -> Unit,
    updateAmount: (Long) -> Unit,
    updateUnitValue: (Double) -> Unit,
    addOrEditTransaction: () -> Unit,
    onFinish: () -> Unit,
    transactionId: Long? = null
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DefaultOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            text = uiState.title,
            onValueChange = updateTitle,
            labelText = "Title"
        )

        TextFieldWithDropDown(
            modifier = Modifier.fillMaxWidth(),
            dropDownValues = TransactionCategory.values().map { it.name },
            text = uiState.category?.name.orEmpty(),
            labelText = "Category",
            onSelectedItem = updateCategory
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextFieldWithDropDown(
                modifier = Modifier.weight(1f),
                dropDownValues = TransactionType.values().map { it.name },
                text = uiState.type?.name.orEmpty(),
                labelText = "Type",
                onSelectedItem = updateType
            )
            Spacer(modifier = Modifier.padding(6.dp))

            TextFieldWithDatePicker(
                modifier = Modifier.weight(1f),
                text = uiState.date.toBrazilianDateFormat(),
                onSelectedDateMillis = updateDate
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultOutlinedTextField(
                modifier = Modifier.weight(1f),
                text = uiState.amount.toString(),
                onValueChange = {
                    it.runCatching {
                        val amount = if (it.isBlank()) 0 else this.toLong()
                        updateAmount(amount)
                    }
                },
                labelText = "Amount",
                keyboardType = KeyboardType.Number
            )

            Spacer(modifier = Modifier.padding(6.dp))

            DefaultOutlinedTextField(
                modifier = Modifier.weight(2f),
                text = uiState.unitValue.toString(),
                onValueChange = {
                    it.runCatching {
                        val unitValue = this.toDouble()
                        val formattedUnitValue = String.format("%.2f",unitValue).toDouble()
                        updateUnitValue(formattedUnitValue)
                    }
                },
                labelText = "Unit value",
                keyboardType = KeyboardType.Decimal
            )
        }

        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = "total",
            color = Color.White,
            fontSize = 20.sp
        )
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = uiState.totalValue.toCurrencyString("pt-br"),
            color = Color.White,
            fontSize = 32.sp
        )

        GradientButton(
            modifier = Modifier
                .padding(top = 30.dp, start = 40.dp, end = 40.dp)
                .width(260.dp)
                .height(40.dp),
            onClickListener = addOrEditTransaction,
            text = if (transactionId == null) "Add" else "Edit",
            brush = Brush.verticalGradient(
                listOf(
                    GradGreenButton1, GradGreenButton2, GradGreenButton3
                )
            )
        )

        OutlinedButton(
            modifier = Modifier
                .padding(top = 10.dp, start = 40.dp, end = 40.dp)
                .width(260.dp)
                .height(40.dp),
            onClick = onFinish,
            border = BorderStroke(1.dp, OutlinedGreen),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = OutlinedGreen)
        ) {
            Text(
                text = "Cancel",
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun AddEditTransactionScreenPreview() {
    CashWiseTheme {
        AddEditTransactionScreen(
            modifier = Modifier.padding(26.dp),
            uiState = AddEditTransactionUIState(
                title = "Sample",
                category = TransactionCategory.SAVINGS,
                type = TransactionType.PROFIT,
                date = Date().time,
                amount = 8L,
                unitValue = 15.11,
                totalValue = 120.88
            ),
            updateTitle = {},
            updateType = {},
            updateCategory = {},
            updateDate = {},
            updateAmount = {},
            updateUnitValue = {},
            addOrEditTransaction = {},
            onFinish = {}
        )
    }
}