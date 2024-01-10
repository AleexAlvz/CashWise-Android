package com.aleexalvz.cashwise.data.model.statement

import com.aleexalvz.cashwise.data.model.investment.Investment
import com.aleexalvz.cashwise.data.model.investment.InvestmentCategory
import com.aleexalvz.cashwise.data.model.investment.InvestmentType
import com.aleexalvz.cashwise.data.model.investment.totalValue
import com.aleexalvz.cashwise.helper.toBrazilianDateFormat
import com.aleexalvz.cashwise.helper.toCurrencyString

data class Statement(
    val id: Long,
    val title: String,
    val category: InvestmentCategory,
    val totalValue: String,
    val type: InvestmentType,
    val date: String
)

fun Investment.toStatement(): Statement = Statement(
    id = id,
    title = title,
    category = category,
    totalValue = totalValue().toCurrencyString(),
    type = type,
    date = dateMillis.toBrazilianDateFormat()
)