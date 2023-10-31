package com.aleexalvz.cashwise.data.model.statement

import com.aleexalvz.cashwise.data.model.transaction.TransactionCategory
import com.aleexalvz.cashwise.data.model.transaction.TransactionType

data class Statement(
    val id: Long,
    val title: String,
    val category: TransactionCategory,
    val totalValue: String,
    val type: TransactionType,
    val date: String
)