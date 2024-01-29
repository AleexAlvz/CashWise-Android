package com.aleexalvz.cashwise.data.model.investment

data class Investment(
    val id: Long,
    val userID: Long,
    val title: String,
    val category: InvestmentCategory,
    val unitValue: Long,
    val amount: Long,
    val type: InvestmentType,
    val dateMillis: Long
)

fun Investment.totalValue(): Long = amount * unitValue

fun List<Investment>.totalValue(): Long = fold(0) { accumulator, investment ->
    when (investment.type) {
        InvestmentType.PROFIT -> (accumulator + investment.totalValue())
        InvestmentType.LOSS -> (accumulator - investment.totalValue())
    }
}
