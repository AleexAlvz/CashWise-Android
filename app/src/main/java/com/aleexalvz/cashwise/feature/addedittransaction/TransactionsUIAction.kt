package com.aleexalvz.cashwise.feature.addedittransaction

sealed class TransactionsUIAction {
    data class FetchTransaction(val id: Long) : TransactionsUIAction()
    data class UpdateTitle(val title: String) : TransactionsUIAction()
    data class UpdateCategory(val category: String) : TransactionsUIAction()
    data class UpdateType(val type: String) : TransactionsUIAction()
    data class UpdateDate(val date: Long) : TransactionsUIAction()
    data class UpdateAmount(val amount: Long) : TransactionsUIAction()
    data class UpdateUnitValue(val unitValue: Double) : TransactionsUIAction()
    data class AddEditTransaction(val transactionID: Long?) : TransactionsUIAction()
    object ClearError : TransactionsUIAction()
}