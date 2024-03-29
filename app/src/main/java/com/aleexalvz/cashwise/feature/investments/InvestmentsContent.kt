package com.aleexalvz.cashwise.feature.investments

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aleexalvz.cashwise.data.model.statement.Statement
import com.aleexalvz.cashwise.data.model.investment.InvestmentCategory
import com.aleexalvz.cashwise.data.model.investment.InvestmentType

@Composable
fun InvestmentsContent(
    modifier: Modifier,
    content: List<Statement>,
    onClickItem: (Long) -> Unit
) {
    LazyColumn(modifier = modifier) {
        content.forEachIndexed { index, statement ->
            item {
                InvestmentsItem(
                    statement = statement,
                    onClickItem = onClickItem
                )
                val spacingValue = if (index == content.lastIndex) 40.dp else 8.dp
                Spacer(modifier = Modifier.padding(spacingValue))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InvestmentsContentPreview() {
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

    InvestmentsContent(
        Modifier.padding(20.dp),
        sampleData
    ) {}
}