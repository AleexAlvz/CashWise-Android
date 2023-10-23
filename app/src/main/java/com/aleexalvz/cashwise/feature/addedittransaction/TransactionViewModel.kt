package com.aleexalvz.cashwise.feature.addedittransaction

import androidx.lifecycle.ViewModel
import com.aleexalvz.cashwise.data.model.transaction.TransactionCategory
import com.aleexalvz.cashwise.data.model.transaction.TransactionType
import com.aleexalvz.cashwise.data.model.transaction.getTransactionCategoryByName
import com.aleexalvz.cashwise.data.model.transaction.getTransactionTypeByName
import com.aleexalvz.cashwise.data.repository.LocalTransactionRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.util.Date
import javax.inject.Inject

data class AddEditTransactionUIState(
    var title: String = "",
    var category: TransactionCategory? = null,
    var type: TransactionType? = null,
    var date: Long = Date().time,
    var amount: Long = 0,
    var unitValue: Double = 0.0
)

@HiltViewModel
class TransactionViewModel @Inject constructor(
    transactionRepository: LocalTransactionRepositoryImpl
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
}