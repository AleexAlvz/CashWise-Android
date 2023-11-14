package com.aleexalvz.cashwise.feature.addedittransaction

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aleexalvz.cashwise.R
import com.aleexalvz.cashwise.components.GradientButton
import com.aleexalvz.cashwise.components.textfield.DefaultOutlinedTextField
import com.aleexalvz.cashwise.components.textfield.TextFieldWithDatePicker
import com.aleexalvz.cashwise.components.textfield.TextFieldWithDropDown
import com.aleexalvz.cashwise.data.model.transaction.TransactionCategory
import com.aleexalvz.cashwise.data.model.transaction.TransactionType
import com.aleexalvz.cashwise.helper.toBrazilianDateFormat
import com.aleexalvz.cashwise.helper.toCurrencyString
import com.aleexalvz.cashwise.ui.theme.CashWiseTheme
import com.aleexalvz.cashwise.ui.theme.GradGreenButton1
import com.aleexalvz.cashwise.ui.theme.GradGreenButton2
import com.aleexalvz.cashwise.ui.theme.GradGreenButton3
import com.aleexalvz.cashwise.ui.theme.OutlinedGreen
import java.math.RoundingMode
import java.util.Date

@Composable
fun TransactionScreen(
    modifier: Modifier,
    transactionId: Long? = null,
    onFinish: () -> Unit,
    viewModel: TransactionViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    TransactionScreen(
        modifier = modifier,
        uiState = uiState,
        onUIAction = viewModel::onUIAction,
        transactionId = transactionId,
        onFinish = onFinish
    )
}

@Composable
fun TransactionScreen(
    modifier: Modifier,
    uiState: TransactionUIState,
    transactionId: Long? = null,
    onFinish: () -> Unit,
    onUIAction: (TransactionsUIAction) -> Unit
) {

    LaunchedEffect(key1 = "fetchTransaction") {
        transactionId?.let { onUIAction(TransactionsUIAction.FetchTransaction(it)) }
    }

    if (transactionId == null || uiState.isTransactionFetched) {
        if (uiState.isSuccessful) onFinish()


        if (uiState.isError) {
            Toast.makeText(LocalContext.current, uiState.errorMessage, Toast.LENGTH_LONG).show()
            onUIAction(TransactionsUIAction.ClearError)
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DefaultOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                text = uiState.title,
                onValueChange = {
                    onUIAction(TransactionsUIAction.UpdateTitle(it))
                },
                labelText = stringResource(R.string.title)
            )

            TextFieldWithDropDown(
                modifier = Modifier.fillMaxWidth(),
                dropDownValues = TransactionCategory.values().map { it.name },
                text = uiState.category?.name.orEmpty(),
                labelText = stringResource(R.string.category),
                onSelectedItem = {
                    onUIAction(TransactionsUIAction.UpdateCategory(it))
                }
            )

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextFieldWithDropDown(
                    modifier = Modifier.weight(1f),
                    dropDownValues = TransactionType.values().map { it.name },
                    text = uiState.type?.name.orEmpty(),
                    labelText = stringResource(R.string.type),
                    onSelectedItem = {
                        onUIAction(TransactionsUIAction.UpdateType(it))
                    }
                )
                Spacer(modifier = Modifier.padding(6.dp))

                TextFieldWithDatePicker(
                    modifier = Modifier.weight(1f),
                    text = uiState.date.toBrazilianDateFormat(),
                    onSelectedDateMillis = {
                        onUIAction(TransactionsUIAction.UpdateDate(it))
                    }
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
                            onUIAction(TransactionsUIAction.UpdateAmount(amount))
                        }
                    },
                    labelText = stringResource(R.string.amount),
                    keyboardType = KeyboardType.Number
                )

                Spacer(modifier = Modifier.padding(6.dp))

                DefaultOutlinedTextField(
                    modifier = Modifier.weight(2f),
                    text = uiState.unitValue.toString(),
                    onValueChange = {
                        it.runCatching {
                            val unitValue =
                                this.toBigDecimal().setScale(2, RoundingMode.DOWN).toDouble()
                            onUIAction(TransactionsUIAction.UpdateUnitValue(unitValue))
                        }
                    },
                    labelText = stringResource(R.string.unit_value),
                    keyboardType = KeyboardType.Decimal
                )
            }

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(R.string.total),
                color = Color.White,
                fontSize = 20.sp
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = uiState.totalValue.toCurrencyString(),
                color = Color.White,
                fontSize = 32.sp
            )

            GradientButton(
                modifier = Modifier
                    .padding(top = 30.dp, start = 40.dp, end = 40.dp)
                    .width(260.dp)
                    .height(40.dp),
                onClickListener = { onUIAction(TransactionsUIAction.AddEditTransaction(transactionId)) },
                text = if (transactionId == null) stringResource(R.string.add) else stringResource(R.string.edit),
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
                    text = stringResource(R.string.cancel),
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun AddEditTransactionScreenPreview() {
    CashWiseTheme {
        TransactionScreen(
            modifier = Modifier.padding(26.dp),
            uiState = TransactionUIState(
                title = stringResource(R.string.sample),
                category = TransactionCategory.SAVINGS,
                type = TransactionType.PROFIT,
                date = Date().time,
                amount = 8L,
                unitValue = 15.11,
                totalValue = 120.88
            ),
            onUIAction = {},
            onFinish = {}
        )
    }
}
