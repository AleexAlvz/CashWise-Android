package com.aleexalvz.cashwise.data.repository

import com.aleexalvz.cashwise.data.source.local.model.LocalTransaction

interface LocalTransactionRepository {

    suspend fun getAll(): List<LocalTransaction>

    suspend fun getByID(id: Long): LocalTransaction

    suspend fun insert(transaction: LocalTransaction)

    suspend fun delete(transaction: LocalTransaction)

    suspend fun update(transaction: LocalTransaction)
}