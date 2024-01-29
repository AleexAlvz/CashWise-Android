package com.aleexalvz.cashwise.data.repository

import com.aleexalvz.cashwise.data.model.investment.Investment

interface LocalInvestmentRepository {

    suspend fun getAll(): List<Investment>

    suspend fun getByID(id: Long): Investment

    suspend fun insert(investment: Investment)

    suspend fun delete(investment: Investment)

    suspend fun update(investment: Investment)
}