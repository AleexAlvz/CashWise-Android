package com.aleexalvz.cashwise.data.model.investment

enum class InvestmentType(val title: String) {
    PROFIT("Profit"),
    LOSS("Loss")
}

fun getInvestmentTypeByTitle(title: String): InvestmentType? {
    InvestmentType.values().forEach {
        if (it.title == title) return it
    }
    return null
}
