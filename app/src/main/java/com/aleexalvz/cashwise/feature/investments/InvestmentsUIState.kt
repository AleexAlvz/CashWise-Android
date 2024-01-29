package com.aleexalvz.cashwise.feature.investments

import com.aleexalvz.cashwise.data.model.statement.Statement

data class InvestmentsUIState(
    val content: List<Statement> = listOf(),
    var isLoading: Boolean = false,
    var isError: Boolean = false
)