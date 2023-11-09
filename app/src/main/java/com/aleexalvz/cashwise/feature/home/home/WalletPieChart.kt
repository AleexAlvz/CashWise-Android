package com.aleexalvz.cashwise.feature.home.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aleexalvz.cashwise.components.graph.PieChart
import com.aleexalvz.cashwise.components.graph.PieChartItem
import com.aleexalvz.cashwise.data.model.transaction.color

@Composable
fun WalletPieChart(
    modifier: Modifier = Modifier,
    wallet: Wallet
) {

    val pieChartData: List<PieChartItem> = wallet.map { it.second.toFloat() to it.first.color() }

    PieChart(modifier = modifier, data = pieChartData)
}