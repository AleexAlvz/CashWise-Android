package com.aleexalvz.cashwise.feature.investmentsform

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.aleexalvz.cashwise.R
import com.aleexalvz.cashwise.components.GradientButton
import com.aleexalvz.cashwise.components.textfield.DefaultOutlinedTextField
import com.aleexalvz.cashwise.components.textfield.TextFieldWithDatePicker
import com.aleexalvz.cashwise.components.textfield.TextFieldWithDropDown
import com.aleexalvz.cashwise.data.model.investment.InvestmentCategory
import com.aleexalvz.cashwise.data.model.investment.InvestmentType
import com.aleexalvz.cashwise.helper.getCurrencySymbol
import com.aleexalvz.cashwise.helper.toBrazilianDateFormat
import com.aleexalvz.cashwise.helper.toCurrencyLong
import com.aleexalvz.cashwise.helper.toCurrencyString
import com.aleexalvz.cashwise.ui.theme.CashWiseTheme
import com.aleexalvz.cashwise.ui.theme.GradGreenButton1
import com.aleexalvz.cashwise.ui.theme.GradGreenButton2
import com.aleexalvz.cashwise.ui.theme.GradGreenButton3
import com.aleexalvz.cashwise.ui.theme.OutlinedGreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import java.util.Date

@Composable
fun InvestmentsFormScreen(
    modifier: Modifier,
    investmentId: Long? = null,
    onFinish: () -> Unit,
    viewModel: InvestmentsFormViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiEvent = viewModel.uiEvents

    InvestmentsFormScreen(
        modifier = modifier,
        uiState = uiState,
        onUIAction = viewModel::onUIAction,
        uiEvent = uiEvent,
        investmentId = investmentId,
        onFinish = onFinish
    )
}

@Composable
fun InvestmentsFormScreen(
    modifier: Modifier,
    uiState: InvestmentsFormUIState,
    uiEvent: SharedFlow<InvestmentsFormUIEvent>,
    investmentId: Long? = null,
    onFinish: () -> Unit,
    onUIAction: (InvestmentsFormUIAction) -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = investmentId) {
        investmentId?.let { onUIAction(InvestmentsFormUIAction.FetchInvestment(it)) }
    }

    ObserveAsEvents(flow = uiEvent) { event ->
        when (event) {
            is InvestmentsFormUIEvent.OnRequestError -> {
                Toast.makeText(context, event.message, Toast.LENGTH_LONG).show()
            }

            is InvestmentsFormUIEvent.OnSuccessfulInvestmentsForm -> onFinish()
        }
    }

    if (investmentId != null && !uiState.isInvestmentFetched) {
        onUIAction(InvestmentsFormUIAction.FetchInvestment(investmentId))
    }

    if (uiState.isLoading.not()) {
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
                    onUIAction(InvestmentsFormUIAction.UpdateTitle(it))
                },
                labelText = stringResource(R.string.title)
            )

            TextFieldWithDropDown(
                modifier = Modifier.fillMaxWidth(),
                dropDownValues = InvestmentCategory.values().map { it.title },
                text = uiState.category?.title.orEmpty(),
                labelText = stringResource(R.string.category),
                onSelectedItem = {
                    onUIAction(InvestmentsFormUIAction.UpdateCategory(it))
                }
            )

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextFieldWithDropDown(
                    modifier = Modifier.weight(1f),
                    dropDownValues = InvestmentType.values().map { it.title },
                    text = uiState.type?.title.orEmpty(),
                    labelText = stringResource(R.string.type),
                    onSelectedItem = {
                        onUIAction(InvestmentsFormUIAction.UpdateType(it))
                    }
                )
                Spacer(modifier = Modifier.padding(6.dp))

                TextFieldWithDatePicker(
                    modifier = Modifier.weight(1f),
                    text = uiState.date.toBrazilianDateFormat(),
                    onSelectedDateMillis = {
                        onUIAction(InvestmentsFormUIAction.UpdateDate(it))
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
                            onUIAction(InvestmentsFormUIAction.UpdateAmount(amount))
                        }
                    },
                    labelText = stringResource(R.string.amount),
                    keyboardType = KeyboardType.Number
                )

                Spacer(modifier = Modifier.padding(6.dp))

                DefaultOutlinedTextField(
                    modifier = Modifier.weight(2f),
                    text = uiState.unitValue.toCurrencyString(),
                    onValueChange = {
                        it.runCatching {
                            onUIAction(
                                InvestmentsFormUIAction.UpdateUnitValue(this.toCurrencyLong())
                            )
                        }
                    },
                    prefix = getCurrencySymbol(),
                    labelText = stringResource(R.string.unit_value),
                    keyboardType = KeyboardType.Number
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
                onClickListener = { onUIAction(InvestmentsFormUIAction.SendInvestment(investmentId)) },
                text = if (investmentId == null) stringResource(R.string.add) else stringResource(R.string.edit),
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

@Composable
private fun <T> ObserveAsEvents(flow: Flow<T>, onEvent: (T) -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(flow, lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect(onEvent)
        }
    }
}

@Preview
@Composable
fun InvestmentsFormPreview() {
    CashWiseTheme {
        InvestmentsFormScreen(
            modifier = Modifier.padding(26.dp),
            uiState = InvestmentsFormUIState(
                title = stringResource(R.string.sample),
                category = InvestmentCategory.SAVINGS,
                type = InvestmentType.PROFIT,
                date = Date().time,
                amount = 8L,
                unitValue = 1511,
                totalValue = 12088
            ),
            onUIAction = {},
            uiEvent = MutableSharedFlow(),
            onFinish = {}
        )
    }
}
