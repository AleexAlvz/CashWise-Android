package com.aleexalvz.cashwise.feature.investments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aleexalvz.cashwise.data.model.statement.Statement
import com.aleexalvz.cashwise.data.model.statement.toStatement
import com.aleexalvz.cashwise.data.repository.LocalInvestmentRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvestmentsViewModel @Inject constructor(
    private val investmentRepository: LocalInvestmentRepositoryImpl
) : ViewModel() {

    private val _uiState = MutableStateFlow(InvestmentsUIState())
    val uiState: StateFlow<InvestmentsUIState> = _uiState

    fun fetchContent() {
        viewModelScope.launch {
            val content = investmentRepository.getAll().map { it.toStatement() }.sortedByDescending { it.date }
            _uiState.update {
                it.copy(content = content)
            }
        }
    }
}