package com.aleexalvz.cashwise.feature.addedittransaction

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.aleexalvz.cashwise.data.model.transaction.TransactionCategory
import com.aleexalvz.cashwise.data.model.transaction.TransactionType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Date

data class AddEditTransactionUIState(
    val title: String = "",
    val category: TransactionCategory? = null,
    val type: TransactionType? = null,
    val date: Date? = Date(),
    val amount: Long = 0,
    val unitValue: Double = 0.0
)

class AddEditTransactionViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(AddEditTransactionUIState())
    val uiState: StateFlow<AddEditTransactionUIState> = _uiState
}