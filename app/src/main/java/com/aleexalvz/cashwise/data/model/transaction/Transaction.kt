package com.aleexalvz.cashwise.data.model.transaction

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Transaction(
    @PrimaryKey
    val id: Long,
    val title: String,
    val category: TransactionCategory,
    val unitValue: Double,
    val unit: Int,
    val type: TransactionType,
    val date: Date
)
