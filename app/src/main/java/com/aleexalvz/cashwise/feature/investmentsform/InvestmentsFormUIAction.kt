package com.aleexalvz.cashwise.feature.investmentsform

sealed class InvestmentsFormUIAction {
    data class FetchInvestment(val id: Long) : InvestmentsFormUIAction()
    data class UpdateTitle(val title: String) : InvestmentsFormUIAction()
    data class UpdateCategory(val category: String) : InvestmentsFormUIAction()
    data class UpdateType(val type: String) : InvestmentsFormUIAction()
    data class UpdateDate(val date: Long) : InvestmentsFormUIAction()
    data class UpdateAmount(val amount: Long) : InvestmentsFormUIAction()
    data class UpdateUnitValue(val unitValue: Double) : InvestmentsFormUIAction()
    data class SendInvestment(val investmentID: Long?) : InvestmentsFormUIAction()
}