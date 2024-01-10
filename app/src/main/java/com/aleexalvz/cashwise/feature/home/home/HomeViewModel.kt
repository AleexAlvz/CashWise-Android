package com.aleexalvz.cashwise.feature.home.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aleexalvz.cashwise.data.model.investment.Investment
import com.aleexalvz.cashwise.data.model.investment.totalValue
import com.aleexalvz.cashwise.data.repository.LocalInvestmentRepositoryImpl
import com.aleexalvz.cashwise.helper.toCurrencyString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUIState(
    val totalBalance: String = "",
    val investments: Investments = listOf(),
    val isFetchedData: Boolean = false
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val investmentsRepository: LocalInvestmentRepositoryImpl
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState: StateFlow<HomeUIState> = _uiState

    fun fetchScreenData() {
        viewModelScope.launch {

            val investmentList = investmentsRepository.getAll()

            val totalBalance: Double = investmentList.totalValue()

            _uiState.update {
                it.copy(
                    totalBalance = totalBalance.toCurrencyString(),
                    investments = investmentList.toInvestments(),
                    isFetchedData = true
                )
            }
        }
    }
}

fun List<Investment>.toInvestments() = this
    .groupBy { it.category }
    .mapValues { it.value.totalValue() }
    .toList()
    .filter { it.second > 0.0 }
