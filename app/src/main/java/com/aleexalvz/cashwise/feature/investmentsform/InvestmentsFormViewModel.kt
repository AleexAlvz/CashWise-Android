package com.aleexalvz.cashwise.feature.investmentsform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aleexalvz.cashwise.data.model.investment.Investment
import com.aleexalvz.cashwise.data.model.investment.InvestmentType
import com.aleexalvz.cashwise.data.model.investment.getInvestmentCategoryByTitle
import com.aleexalvz.cashwise.data.model.investment.getInvestmentTypeByTitle
import com.aleexalvz.cashwise.data.model.investment.totalValue
import com.aleexalvz.cashwise.data.repository.LocalInvestmentRepositoryImpl
import com.aleexalvz.cashwise.foundation.UserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvestmentsFormViewModel @Inject constructor(
    private val investmentRepository: LocalInvestmentRepositoryImpl
) : ViewModel() {

    private val _uiState = MutableStateFlow(InvestmentsFormUIState())
    val uiState: StateFlow<InvestmentsFormUIState> = _uiState

    private val _uiEvents = MutableSharedFlow<InvestmentsFormUIEvent>()
    val uiEvents = _uiEvents.asSharedFlow()

    fun onUIAction(uiEvent: InvestmentsFormUIAction) {
        when (uiEvent) {
            is InvestmentsFormUIAction.FetchInvestment -> fetchInvestmentByID(uiEvent.id)
            is InvestmentsFormUIAction.UpdateTitle -> updateTitle(uiEvent.title)
            is InvestmentsFormUIAction.UpdateCategory -> updateCategory(uiEvent.category)
            is InvestmentsFormUIAction.UpdateType -> updateType(uiEvent.type)
            is InvestmentsFormUIAction.UpdateDate -> updateDate(uiEvent.date)
            is InvestmentsFormUIAction.UpdateAmount -> updateAmount(uiEvent.amount)
            is InvestmentsFormUIAction.UpdateUnitValue -> updateUnitValue(uiEvent.unitValue)
            is InvestmentsFormUIAction.SendInvestment -> sendInvestment(uiEvent.investmentID)
        }
    }

    private fun updateTitle(title: String) {
        _uiState.update {
            it.copy(title = title)
        }
    }

    private fun updateCategory(categoryString: String) =
        getInvestmentCategoryByTitle(categoryString)?.let { category ->
            _uiState.update { it.copy(category = category) }
        }

    private fun updateType(typeString: String) = getInvestmentTypeByTitle(typeString)?.let { type ->
        _uiState.update {
            it.copy(type = type)
        }
    }

    private fun updateDate(dateMillis: Long) {
        _uiState.update {
            it.copy(date = dateMillis)
        }
    }

    private fun updateAmount(amount: Long) {
        _uiState.update {
            it.copy(amount = amount)
        }
        updateTotalValue()
    }

    private fun updateUnitValue(unitValue: Long) {
        _uiState.update {
            it.copy(unitValue = unitValue)
        }
        updateTotalValue()
    }

    private fun updateTotalValue() {
        _uiState.run {
            val totalValue = (value.amount * value.unitValue)
            update {
                it.copy(totalValue = totalValue)
            }
        }
    }

    private fun fetchInvestmentByID(id: Long) = viewModelScope.launch {
        val investment = investmentRepository.getByID(id)
        _uiState.update {
            it.copy(
                title = investment.title,
                category = investment.category,
                type = investment.type,
                date = investment.dateMillis,
                amount = investment.amount,
                unitValue = investment.unitValue,
                totalValue = investment.totalValue(),
                isInvestmentFetched = true
            )
        }
    }

    private suspend fun getInvestment(investmentId: Long? = null): Investment? {
        val state = uiState.value
        if (state.title.isNotEmpty() && state.unitValue > 0 && state.amount > 0 && state.date > 0) {
            val investment =
                UserManager.loggedUser?.userID?.let { userID ->
                    uiState.value.category?.let {
                        uiState.value.type?.let { it1 ->
                            Investment(
                                id = investmentId ?: 0,
                                userID = userID,
                                title = uiState.value.title,
                                category = it,
                                unitValue = uiState.value.unitValue,
                                amount = uiState.value.amount,
                                type = it1,
                                dateMillis = uiState.value.date
                            )
                        }
                    }
                }
            if (investment != null && canBeLoss(investment)) {
                return investment
            } else _uiEvents.emit(InvestmentsFormUIEvent.OnRequestError("You can't loss more that you have on this category"))
        }

        return null
    }

    private fun sendInvestment(investmentId: Long? = null) = viewModelScope.launch {
        runCatching {
            getInvestment(investmentId)?.let { investment ->
                if (investmentId == null) {
                    investmentRepository.insert(investment)
                } else {
                    investmentRepository.update(investment)
                }
                _uiEvents.emit(InvestmentsFormUIEvent.OnSuccessfulInvestmentsForm)
            }
        }.onFailure {
            _uiEvents.emit(InvestmentsFormUIEvent.OnRequestError("Unknown error. Please, try again"))
        }
    }

    private suspend fun canBeLoss(investment: Investment): Boolean {
        if (investment.type == InvestmentType.LOSS) {
            val totalValueFromCategory = investmentRepository.getAll()
                .filter { it.category == investment.category }
                .totalValue()

            if (totalValueFromCategory < investment.totalValue()) {
                return false
            }
        }
        return true
    }
}