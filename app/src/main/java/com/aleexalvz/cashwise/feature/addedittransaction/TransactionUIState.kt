package com.aleexalvz.cashwise.feature.addedittransaction

import com.aleexalvz.cashwise.data.model.transaction.TransactionCategory
import com.aleexalvz.cashwise.data.model.transaction.TransactionType
import java.util.Date

data class TransactionUIState(
    var title: String = "",
    var category: TransactionCategory? = null,
    var type: TransactionType? = null,
    var date: Long = Date().time,
    var amount: Long = 0,
    var unitValue: Double = 0.0,
    var totalValue: Double = 0.0,
    var isSuccessful: Boolean = false,
    var isError: Boolean = false,
    var errorMessage: String = "",
    var isLoading: Boolean = false,
    var isTransactionFetched: Boolean = false
)