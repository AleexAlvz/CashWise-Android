package com.aleexalvz.cashwise.data.repository

import com.aleexalvz.cashwise.data.model.auth.UserNotFoundException
import com.aleexalvz.cashwise.data.model.transaction.Transaction
import com.aleexalvz.cashwise.data.source.local.dao.TransactionDao
import com.aleexalvz.cashwise.data.source.local.model.toLocalTransaction
import com.aleexalvz.cashwise.data.source.local.model.toTransaction
import com.aleexalvz.cashwise.foundation.UserManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalTransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao
) : LocalTransactionRepository {
    override suspend fun getAll(): List<Transaction> {
        return withContext(Dispatchers.IO) {
            val userID = UserManager.loggedUser?.userID
                ?: throw UserNotFoundException("On trying to get id, user not found. Need be logged!")
            transactionDao.getByUserID(userID).map { it.toTransaction() }
        }
    }

    override suspend fun getByID(id: Long): Transaction {
        return withContext(Dispatchers.IO) {
            transactionDao.getByID(id).toTransaction()
        }
    }

    override suspend fun insert(transaction: Transaction) {
        withContext(Dispatchers.IO) {
            transactionDao.insert(transaction.toLocalTransaction())
        }
    }

    override suspend fun delete(transaction: Transaction) {
        withContext(Dispatchers.IO) {
            transactionDao.delete(transaction.toLocalTransaction())
        }
    }

    override suspend fun update(transaction: Transaction) {
        withContext(Dispatchers.IO) {
            transactionDao.update(transaction.toLocalTransaction())
        }
    }
}