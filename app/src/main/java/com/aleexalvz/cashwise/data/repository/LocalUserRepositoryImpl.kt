package com.aleexalvz.cashwise.data.repository

import com.aleexalvz.cashwise.data.source.local.dao.UserDao
import com.aleexalvz.cashwise.data.source.local.model.LocalUser
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalUserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : LocalUserRepository {
    override suspend fun getByEmail(email: String): LocalUser {
        return withContext(IO) {
            userDao.getByEmail(email)
        }
    }

    override suspend fun register(user: LocalUser) {
        withContext(IO) {
            userDao.register(user)
        }
    }

    override suspend fun update(user: LocalUser) {
        return withContext(IO) {
            userDao.update(user)
        }
    }
}