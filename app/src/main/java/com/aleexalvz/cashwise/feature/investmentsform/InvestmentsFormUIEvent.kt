package com.aleexalvz.cashwise.feature.investmentsform

sealed class InvestmentsFormUIEvent {
    data class OnRequestError(val message: String) : InvestmentsFormUIEvent()
    object OnSuccessfulInvestmentsForm : InvestmentsFormUIEvent()
}