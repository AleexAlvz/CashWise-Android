package com.aleexalvz.cashwise.data.model.transaction

enum class TransactionType {
    PROFIT, LOSS
}

fun getTransactionTypeByName(name: String): TransactionType? {
    TransactionType.values().forEach {
        if (it.name == name) return it
    }
    return null
}
