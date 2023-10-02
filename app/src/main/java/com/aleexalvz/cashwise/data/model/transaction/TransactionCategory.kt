package com.aleexalvz.cashwise.data.model.transaction

enum class TransactionCategory {
    STOCKS, REAL_STATE, FIXED_INCOME, SAVINGS, TREASURE_BONDS, OTHERS
}

fun getTransactionCategoryByName(name: String): TransactionCategory? {
    TransactionCategory.values().forEach {
        if (it.name == name) return it
    }
    return null
}
