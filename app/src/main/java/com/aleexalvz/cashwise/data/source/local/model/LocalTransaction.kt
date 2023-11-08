package com.aleexalvz.cashwise.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aleexalvz.cashwise.data.model.transaction.Transaction
import com.aleexalvz.cashwise.data.model.transaction.TransactionCategory
import com.aleexalvz.cashwise.data.model.transaction.TransactionType

@Entity
data class LocalTransaction(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userID: Long,
    val title: String,
    val category: TransactionCategory,
    val unitValue: Double,
    val amount: Long,
    val type: TransactionType,
    val dateMillis: Long
)

fun LocalTransaction.toTransaction() = Transaction(
    id = id,
    userID = userID,
    title = title,
    category = category,
    unitValue = unitValue,
    amount = amount,
    type = type,
    dateMillis = dateMillis
)

fun Transaction.toLocalTransaction() = LocalTransaction(
    id = id,
    userID = userID,
    title = title,
    category = category,
    unitValue = unitValue,
    amount = amount,
    type = type,
    dateMillis = dateMillis
)