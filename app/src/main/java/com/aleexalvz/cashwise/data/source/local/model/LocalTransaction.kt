package com.aleexalvz.cashwise.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class LocalTransaction(
    @PrimaryKey
    val id: Long,
    val title: String,
    val category: LocalTransactionCategory,
    val unitValue: Double,
    val amount: Int,
    val type: LocalTransactionType,
    val date: Date
)

enum class LocalTransactionCategory {
    STOCKS, REAL_STATE, FIXED_INCOME, SAVINGS, TREASURE_BONDS, OTHERS
}

enum class LocalTransactionType {
    PROFIT, LOSS
}
