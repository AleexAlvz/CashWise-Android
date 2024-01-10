package com.aleexalvz.cashwise.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aleexalvz.cashwise.data.model.investment.Investment
import com.aleexalvz.cashwise.data.model.investment.InvestmentCategory
import com.aleexalvz.cashwise.data.model.investment.InvestmentType

@Entity
data class LocalInvestment(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userID: Long,
    val title: String,
    val category: InvestmentCategory,
    val unitValue: Double,
    val amount: Long,
    val type: InvestmentType,
    val dateMillis: Long
)

fun LocalInvestment.toInvestment() = Investment(
    id = id,
    userID = userID,
    title = title,
    category = category,
    unitValue = unitValue,
    amount = amount,
    type = type,
    dateMillis = dateMillis
)

fun Investment.toLocalInvestment() = LocalInvestment(
    id = id,
    userID = userID,
    title = title,
    category = category,
    unitValue = unitValue,
    amount = amount,
    type = type,
    dateMillis = dateMillis
)