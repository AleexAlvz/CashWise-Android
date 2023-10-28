package com.aleexalvz.cashwise.data.repository

import com.aleexalvz.cashwise.data.source.local.dao.TransactionDao
import com.aleexalvz.cashwise.data.source.local.model.LocalTransaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalTransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao
) : LocalTransactionRepository {
    override suspend fun getAll(): List<LocalTransaction> {
        return withContext(Dispatchers.IO) {
            transactionDao.getAll()
        }
    }

    override suspend fun getByID(id: Long): LocalTransaction {
        return withContext(Dispatchers.IO) {
            transactionDao.getByID(id)
        }
    }

    override suspend fun insert(transaction: LocalTransaction) {
        withContext(Dispatchers.IO) {
            transactionDao.insert(transaction)
        }
    }

    override suspend fun delete(transaction: LocalTransaction) {
        withContext(Dispatchers.IO) {
            transactionDao.delete(transaction)
        }
    }

    override suspend fun update(transaction: LocalTransaction) {
        withContext(Dispatchers.IO) {
            transactionDao.update(transaction)
        }
    }
}