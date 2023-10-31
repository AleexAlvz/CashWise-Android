package com.aleexalvz.cashwise.feature.home.statement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aleexalvz.cashwise.data.model.statement.Statement
import com.aleexalvz.cashwise.data.repository.LocalTransactionRepositoryImpl
import com.aleexalvz.cashwise.helper.toBrazilianDateFormat
import com.aleexalvz.cashwise.helper.toCurrencyString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatementViewModel @Inject constructor(
    private val transactionRepository: LocalTransactionRepositoryImpl
) : ViewModel() {

    private val _uiState = MutableStateFlow(StatementUIState())
    val uiState: StateFlow<StatementUIState> = _uiState

    fun fetchContent() {
        viewModelScope.launch {
            val content = transactionRepository.getAll().map {
                Statement(
                    id = it.id,
                    title = it.title,
                    category = it.category,
                    totalValue = (it.amount * it.unitValue).toCurrencyString(),
                    type = it.type,
                    date = it.dateMillis.toBrazilianDateFormat()
                )
            }
            _uiState.update {
                it.copy(content = content)
            }
        }
    }
}