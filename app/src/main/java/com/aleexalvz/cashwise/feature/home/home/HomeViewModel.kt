package com.aleexalvz.cashwise.feature.home.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aleexalvz.cashwise.data.model.transaction.TransactionCategory
import com.aleexalvz.cashwise.data.model.transaction.totalValue
import com.aleexalvz.cashwise.data.repository.LocalTransactionRepositoryImpl
import com.aleexalvz.cashwise.helper.toCurrencyString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

typealias Wallet = List<Pair<TransactionCategory, Double>>

data class HomeUIState(
    val totalBalance: String = "",
    val wallet: Wallet = listOf(),
    val isFetchedData: Boolean = false
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val transactionRepository: LocalTransactionRepositoryImpl
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState: StateFlow<HomeUIState> = _uiState

    fun fetchScreenData() {
        viewModelScope.launch {

            val transactions = transactionRepository.getAll()

            val totalBalance: Double = transactions
                .map { it.totalValue() }
                .reduce { acc, transactionTotalValue ->
                    acc + transactionTotalValue
                }

            val wallet: Wallet = transactions
                .groupBy { it.category }
                .mapValues {
                    it.value.sumOf { transaction ->
                        transaction.totalValue()
                    }
                }.toList()

            _uiState.update {
                it.copy(
                    totalBalance = totalBalance.toCurrencyString(),
                    wallet = wallet,
                    isFetchedData = true
                )
            }
        }
    }
}
