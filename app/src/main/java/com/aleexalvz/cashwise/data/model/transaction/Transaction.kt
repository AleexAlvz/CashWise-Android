package com.aleexalvz.cashwise.data.model.transaction

data class Transaction(
    val id: Long,
    val title: String,
    val category: TransactionCategory,
    val unitValue: Double,
    val amount: Int,
    val type: TransactionType,
    val dateMillis: Long
)
