package com.aleexalvz.cashwise.feature.addedittransaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aleexalvz.cashwise.data.model.transaction.Transaction
import com.aleexalvz.cashwise.data.model.transaction.TransactionType
import com.aleexalvz.cashwise.data.model.transaction.getTransactionCategoryByName
import com.aleexalvz.cashwise.data.model.transaction.getTransactionTypeByName
import com.aleexalvz.cashwise.data.model.transaction.totalValue
import com.aleexalvz.cashwise.data.repository.LocalTransactionRepositoryImpl
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
class TransactionViewModel @Inject constructor(
    private val transactionRepository: LocalTransactionRepositoryImpl
) : ViewModel() {

    private val _uiState = MutableStateFlow(TransactionUIState())
    val uiState: StateFlow<TransactionUIState> = _uiState

    private val _uiEvents = MutableSharedFlow<TransactionUIEvent>()
    val uiEvents = _uiEvents.asSharedFlow()

    fun onUIAction(uiEvent: TransactionsUIAction) {
        when (uiEvent) {
            is TransactionsUIAction.FetchTransaction -> fetchTransactionByID(uiEvent.id)
            is TransactionsUIAction.UpdateTitle -> updateTitle(uiEvent.title)
            is TransactionsUIAction.UpdateCategory -> updateCategory(uiEvent.category)
            is TransactionsUIAction.UpdateType -> updateType(uiEvent.type)
            is TransactionsUIAction.UpdateDate -> updateDate(uiEvent.date)
            is TransactionsUIAction.UpdateAmount -> updateAmount(uiEvent.amount)
            is TransactionsUIAction.UpdateUnitValue -> updateUnitValue(uiEvent.unitValue)
            is TransactionsUIAction.AddEditTransaction -> addOrEditTransaction(uiEvent.transactionID)
        }
    }

    private fun updateTitle(title: String) {
        _uiState.update {
            it.copy(title = title)
        }
    }

    private fun updateCategory(categoryString: String) =
        getTransactionCategoryByName(categoryString)?.let { category ->
            _uiState.update { it.copy(category = category) }
        }

    private fun updateType(typeString: String) = getTransactionTypeByName(typeString)?.let { type ->
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

    private fun updateUnitValue(unitValue: Double) {
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

    private fun fetchTransactionByID(id: Long) = viewModelScope.launch {
        val transaction = transactionRepository.getByID(id)
        _uiState.update {
            it.copy(
                title = transaction.title,
                category = transaction.category,
                type = transaction.type,
                date = transaction.dateMillis,
                amount = transaction.amount,
                unitValue = transaction.unitValue,
                totalValue = transaction.totalValue(),
                isTransactionFetched = true
            )
        }
    }

    private suspend fun getTransaction(transactionID: Long? = null): Transaction? {
        val transaction = with(uiState.value) {

            val userID =
                UserManager.loggedUser?.userID!! //TODO think a better way to logged user not be null

            Transaction(
                id = transactionID ?: 0,
                userID = userID,
                title = title,
                category = category!!,
                unitValue = unitValue,
                amount = amount,
                type = type!!,
                dateMillis = date
            )
        }
        return if (!canBeLoss(transaction)) {
            _uiEvents.emit(TransactionUIEvent.OnRequestError("You can't loss more that you have on this category"))
            null
        } else transaction
    }

    private fun addOrEditTransaction(transactionId: Long? = null) = viewModelScope.launch {
        runCatching {
            getTransaction(transactionId)?.let { transaction ->
                if (transactionId == null) {
                    transactionRepository.insert(transaction)
                } else {
                    transactionRepository.update(transaction)
                }
                _uiEvents.emit(TransactionUIEvent.OnSuccessfulTransaction)
            }
        }.onFailure {
            _uiEvents.emit(TransactionUIEvent.OnRequestError("Unknown error. Please, try again"))
        }
    }

    private suspend fun canBeLoss(transaction: Transaction): Boolean {
        if (transaction.type == TransactionType.LOSS) {
            val totalValueFromCategory = transactionRepository.getAll()
                .filter { it.category == transaction.category }
                .totalValue()

            if (totalValueFromCategory < transaction.totalValue()) {
                return false
            }
        }
        return true
    }
}