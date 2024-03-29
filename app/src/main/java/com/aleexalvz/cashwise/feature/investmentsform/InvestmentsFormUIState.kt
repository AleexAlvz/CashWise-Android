package com.aleexalvz.cashwise.feature.investmentsform

import com.aleexalvz.cashwise.data.model.investment.InvestmentCategory
import com.aleexalvz.cashwise.data.model.investment.InvestmentType
import java.util.Date

data class InvestmentsFormUIState(
    var title: String = "",
    var category: InvestmentCategory? = null,
    var type: InvestmentType? = null,
    var date: Long = Date().time,
    var amount: Long = 0,
    var unitValue: Long = 0L,
    var totalValue: Long = 0L,
    var isLoading: Boolean = false,
    var isInvestmentFetched: Boolean = false
)