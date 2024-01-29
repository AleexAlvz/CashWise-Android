package com.aleexalvz.cashwise.feature.home.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aleexalvz.cashwise.components.graph.PieChart
import com.aleexalvz.cashwise.components.graph.PieChartItem
import com.aleexalvz.cashwise.data.model.investment.InvestmentCategory
import com.aleexalvz.cashwise.data.model.investment.color

typealias Investments = List<Pair<InvestmentCategory, Long>>

@Composable
fun InvestmentsPieChart(
    modifier: Modifier = Modifier,
    investments: Investments
) {

    val pieChartData: List<PieChartItem> = investments.map { it.second.toFloat() to it.first.color() }

    PieChart(modifier = modifier, data = pieChartData)
}