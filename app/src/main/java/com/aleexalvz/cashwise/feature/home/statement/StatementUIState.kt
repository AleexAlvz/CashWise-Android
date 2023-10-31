package com.aleexalvz.cashwise.feature.home.statement

import com.aleexalvz.cashwise.data.model.statement.Statement

data class StatementUIState (
    val content: List<Statement> = listOf(),
    var isLoading: Boolean = false,
    var isError: Boolean = false
)

