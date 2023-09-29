package com.aleexalvz.cashwise.data.repository

import com.aleexalvz.cashwise.data.source.local.dao.TransactionDao
import com.aleexalvz.cashwise.data.source.local.model.LocalTransaction
import javax.inject.Inject

class LocalTransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao
) : LocalTransactionRepository {
    override suspend fun getAll(): List<LocalTransaction> {
        return transactionDao.getAll()
    }

    override suspend fun getByID(id: Long): LocalTransaction {
        return transactionDao.getByID(id)
    }

    override suspend fun insert(transaction: LocalTransaction) {
        transactionDao.insert(transaction)
    }

    override suspend fun delete(transaction: LocalTransaction) {
        transactionDao.delete(transaction)
    }

    override suspend fun update(transaction: LocalTransaction) {
        transactionDao.update(transaction)
    }
}