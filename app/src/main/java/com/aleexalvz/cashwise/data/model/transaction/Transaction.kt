package com.aleexalvz.cashwise.data.model.transaction

data class Transaction(
    val id: Long,
    val userID: Long,
    val title: String,
    val category: TransactionCategory,
    val unitValue: Double,
    val amount: Long,
    val type: TransactionType,
    val dateMillis: Long
)

fun Transaction.totalValue(): Double = amount * unitValue

fun List<Transaction>.totalValue(): Double = fold(0.0) { accumulator, transaction ->
    when (transaction.type) {
        TransactionType.PROFIT -> (accumulator + transaction.totalValue())
        TransactionType.LOSS -> (accumulator - transaction.totalValue())
    }
}
