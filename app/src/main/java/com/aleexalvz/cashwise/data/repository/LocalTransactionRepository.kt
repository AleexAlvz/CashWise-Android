package com.aleexalvz.cashwise.data.repository

import com.aleexalvz.cashwise.data.model.transaction.Transaction
import com.aleexalvz.cashwise.data.source.local.dao.TransactionDao

class LocalTransactionRepository(
    private val transactionDao: TransactionDao
) : TransactionRepository {
    override suspend fun getAll(): List<Transaction> {
        return transactionDao.getAll()
    }

    override suspend fun getByID(id: Long): Transaction {
        return transactionDao.getByID(id)
    }

    override suspend fun insert(transaction: Transaction) {
        transactionDao.insert(transaction)
    }

    override suspend fun delete(transaction: Transaction) {
        transactionDao.delete(transaction)
    }

    override suspend fun update(transaction: Transaction) {
        transactionDao.update(transaction)
    }
}