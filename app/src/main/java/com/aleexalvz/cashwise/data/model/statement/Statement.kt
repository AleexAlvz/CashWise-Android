package com.aleexalvz.cashwise.data.model.statement

import com.aleexalvz.cashwise.data.model.transaction.Transaction
import com.aleexalvz.cashwise.data.model.transaction.TransactionCategory
import com.aleexalvz.cashwise.data.model.transaction.TransactionType
import com.aleexalvz.cashwise.data.model.transaction.totalValue
import com.aleexalvz.cashwise.helper.toBrazilianDateFormat
import com.aleexalvz.cashwise.helper.toCurrencyString

data class Statement(
    val id: Long,
    val title: String,
    val category: TransactionCategory,
    val totalValue: String,
    val type: TransactionType,
    val date: String
)

fun Transaction.toStatement(): Statement = Statement(
    id = id,
    title = title,
    category = category,
    totalValue = totalValue().toCurrencyString(),
    type = type,
    date = dateMillis.toBrazilianDateFormat()
)