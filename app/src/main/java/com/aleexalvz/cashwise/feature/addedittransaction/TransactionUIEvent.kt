package com.aleexalvz.cashwise.feature.addedittransaction

sealed class TransactionUIEvent {
    data class OnRequestError(val message: String) : TransactionUIEvent()
    object OnSuccessfulTransaction : TransactionUIEvent()
}