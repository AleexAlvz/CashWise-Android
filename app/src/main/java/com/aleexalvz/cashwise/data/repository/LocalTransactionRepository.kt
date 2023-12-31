package com.aleexalvz.cashwise.data.repository

import com.aleexalvz.cashwise.data.model.transaction.Transaction

interface LocalTransactionRepository {

    suspend fun getAll(): List<Transaction>

    suspend fun getByID(id: Long): Transaction

    suspend fun insert(transaction: Transaction)

    suspend fun delete(transaction: Transaction)

    suspend fun update(transaction: Transaction)
}