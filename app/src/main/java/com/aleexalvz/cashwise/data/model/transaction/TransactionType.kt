package com.aleexalvz.cashwise.data.model.transaction

enum class TransactionType(val title: String) {
    PROFIT("Profit"),
    LOSS("Loss")
}

fun getTransactionTypeByTitle(title: String): TransactionType? {
    TransactionType.values().forEach {
        if (it.title == title) return it
    }
    return null
}
