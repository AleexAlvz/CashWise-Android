package com.aleexalvz.cashwise.feature.home.statement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aleexalvz.cashwise.data.model.statement.Statement
import com.aleexalvz.cashwise.data.model.statement.toStatement
import com.aleexalvz.cashwise.data.source.local.repository.LocalTransactionRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class StatementUIState(
    val content: List<Statement> = listOf(),
    var isLoading: Boolean = false,
    var isError: Boolean = false
)

@HiltViewModel
class StatementViewModel @Inject constructor(
    private val transactionRepository: LocalTransactionRepositoryImpl
) : ViewModel() {

    private val _uiState = MutableStateFlow(StatementUIState())
    val uiState: StateFlow<StatementUIState> = _uiState

    fun fetchContent() {
        viewModelScope.launch {
            transactionRepository.getAll().onSuccess { transactions ->
                val content = transactions.map { it.toStatement() }.sortedByDescending { it.date }
                _uiState.update {
                    it.copy(content = content)
                }
            }
        }
    }
}