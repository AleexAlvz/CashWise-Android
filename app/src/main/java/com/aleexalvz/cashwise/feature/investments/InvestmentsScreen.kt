package com.aleexalvz.cashwise.feature.investments

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aleexalvz.cashwise.data.model.statement.Statement
import com.aleexalvz.cashwise.data.model.investment.InvestmentCategory
import com.aleexalvz.cashwise.data.model.investment.InvestmentType
import com.aleexalvz.cashwise.ui.theme.CashWiseTheme
import com.aleexalvz.cashwise.ui.theme.Green

@Composable
fun InvestmentsScreen(
    onAddInvestment: () -> Unit,
    onEditInvestment: (Long) -> Unit,
    viewModel: InvestmentsViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    InvestmentsScreen(
        onAddInvestment = onAddInvestment,
        onEditInvestment = onEditInvestment,
        fetchContent = viewModel::fetchContent,
        uiState = uiState
    )
}

@Composable
fun InvestmentsScreen(
    onAddInvestment: () -> Unit,
    onEditInvestment: (Long) -> Unit,
    fetchContent: () -> Unit,
    uiState: InvestmentsUIState
) {

    if (uiState.content.isEmpty()) fetchContent()

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { onAddInvestment() },
                containerColor = Green,
                contentColor = Color.White,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add icon"
                )
                Text(text = "New registration")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.TopCenter
        ) {
            InvestmentsContent(
                modifier = Modifier.padding(20.dp),
                content = uiState.content,
                onClickItem = onEditInvestment
            )
        }
    }
}

@Preview()
@Composable
fun InvestmentsScreenPreview() {
    CashWiseTheme {
        val sampleData: List<Statement> = mutableListOf<Statement>().apply {
            repeat(4) {
                add(
                    Statement(
                        id = 0,
                        title = "Title",
                        category = InvestmentCategory.SAVINGS,
                        totalValue = "R$ 100,00",
                        type = InvestmentType.PROFIT,
                        date = "19/10/2022"
                    )
                )
            }
        }

        InvestmentsScreen(
            onAddInvestment = {},
            onEditInvestment = {},
            fetchContent = {},
            uiState = InvestmentsUIState(content = sampleData)
        )
    }
}