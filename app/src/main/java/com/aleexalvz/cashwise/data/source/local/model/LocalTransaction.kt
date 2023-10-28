package com.aleexalvz.cashwise.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aleexalvz.cashwise.data.model.transaction.TransactionCategory
import com.aleexalvz.cashwise.data.model.transaction.TransactionType
import java.util.Date

@Entity
data class LocalTransaction(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val category: TransactionCategory,
    val unitValue: Double,
    val amount: Long,
    val type: TransactionType,
    val dateMillis: Long
)
