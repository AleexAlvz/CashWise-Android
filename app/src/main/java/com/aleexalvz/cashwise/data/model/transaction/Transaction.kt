package com.aleexalvz.cashwise.data.model.transaction

import java.util.Date

data class Transaction(
    val id: Long,
    val title: String,
    val category: TransactionCategory,
    val unitValue: Double,
    val unit: Int,
    val type: TransactionType,
    val date: Date
)
