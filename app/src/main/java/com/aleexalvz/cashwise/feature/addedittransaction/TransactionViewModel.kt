package com.aleexalvz.cashwise.feature.addedittransaction

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aleexalvz.cashwise.data.model.auth.UserNotFoundException
import com.aleexalvz.cashwise.data.model.transaction.Transaction
import com.aleexalvz.cashwise.data.model.transaction.TransactionCategory
import com.aleexalvz.cashwise.data.model.transaction.TransactionType
import com.aleexalvz.cashwise.data.model.transaction.getTransactionCategoryByName
import com.aleexalvz.cashwise.data.model.transaction.getTransactionTypeByName
import com.aleexalvz.cashwise.data.model.transaction.totalValue
import com.aleexalvz.cashwise.data.repository.LocalTransactionRepositoryImpl
import com.aleexalvz.cashwise.foundation.UserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

data class AddEditTransactionUIState(
    var title: String = "",
    var category: TransactionCategory? = null,
    var type: TransactionType? = null,
    var date: Long = Date().time,
    var amount: Long = 0,
    var unitValue: Double = 0.0,
    var totalValue: Double = 0.0,
    var isSuccessful: Boolean = false,
    var isError: Boolean = false,
    var isLoading: Boolean = false,
    var isTransactionFetched: Boolean = false
)

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val transactionRepository: LocalTransactionRepositoryImpl
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddEditTransactionUIState())
    val uiState: StateFlow<AddEditTransactionUIState> = _uiState

    fun updateTitle(title: String) {
        _uiState.update {
            it.copy(title = title)
        }
    }

    fun updateCategory(categoryString: String) =
        getTransactionCategoryByName(categoryString)?.let { category ->
            _uiState.update { it.copy(category = category) }
        }

    fun updateType(typeString: String) = getTransactionTypeByName(typeString)?.let { type ->
        _uiState.update {
            it.copy(type = type)
        }
    }

    fun updateDate(dateMillis: Long) {
        _uiState.update {
            it.copy(date = dateMillis)
        }
    }

    fun updateAmount(amount: Long) {
        _uiState.update {
            it.copy(amount = amount)
        }
        updateTotalValue()
    }

    fun updateUnitValue(unitValue: Double) {
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

    fun fetchTransactionByID(id: Long) = viewModelScope.launch {
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

    fun addOrEditTransaction(transactionId: Long? = null) = viewModelScope.launch {
        runCatching {
            val userID = UserManager.loggedUser?.userID
                ?: throw UserNotFoundException("On trying to get id, user not found. Need be logged!")

            val transaction = with(uiState.value) {
                Transaction(
                    id = transactionId ?: 0,
                    userID = userID,
                    title = title,
                    category = category!!,
                    unitValue = unitValue,
                    amount = amount,
                    type = type!!,
                    dateMillis = date
                )
            }
            if (transactionId == null) {
                transactionRepository.insert(transaction)
            } else {
                transactionRepository.update(transaction)
            }
        }.onFailure { error ->
            _uiState.update {
                Log.e("TransactionViewModel", error.message.toString())
                it.copy(isError = true)
            }
        }.onSuccess {
            _uiState.update {
                it.copy(isSuccessful = true)
            }
        }
    }
}