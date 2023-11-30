package com.aleexalvz.cashwise.data.repository

import com.aleexalvz.cashwise.data.model.transaction.Transaction

interface TransactionRepository {

    suspend fun getAll(): Result<List<Transaction>>

    suspend fun getByID(id: Long): Result<Transaction>

    suspend fun insert(transaction: Transaction)

    suspend fun delete(transaction: Transaction)

    suspend fun update(transaction: Transaction)
}