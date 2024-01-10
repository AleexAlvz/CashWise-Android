package com.aleexalvz.cashwise.data.repository

import com.aleexalvz.cashwise.data.model.auth.UserNotFoundException
import com.aleexalvz.cashwise.data.model.investment.Investment
import com.aleexalvz.cashwise.data.source.local.dao.InvestmentDao
import com.aleexalvz.cashwise.data.source.local.model.toLocalInvestment
import com.aleexalvz.cashwise.data.source.local.model.toInvestment
import com.aleexalvz.cashwise.foundation.UserManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalInvestmentRepositoryImpl @Inject constructor(
    private val investmentDao: InvestmentDao
) : LocalInvestmentRepository {
    override suspend fun getAll(): List<Investment> {
        return withContext(Dispatchers.IO) {
            val userID = UserManager.loggedUser?.userID
                ?: throw UserNotFoundException("On trying to get id, user not found. Need be logged!")
            investmentDao.getByUserID(userID).map { it.toInvestment() }
        }
    }

    override suspend fun getByID(id: Long): Investment {
        return withContext(Dispatchers.IO) {
            investmentDao.getByID(id).toInvestment()
        }
    }

    override suspend fun insert(investment: Investment) {
        withContext(Dispatchers.IO) {
            investmentDao.insert(investment.toLocalInvestment())
        }
    }

    override suspend fun delete(investment: Investment) {
        withContext(Dispatchers.IO) {
            investmentDao.delete(investment.toLocalInvestment())
        }
    }

    override suspend fun update(investment: Investment) {
        withContext(Dispatchers.IO) {
            investmentDao.update(investment.toLocalInvestment())
        }
    }
}