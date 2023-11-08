package com.aleexalvz.cashwise.data.repository

import android.content.SharedPreferences
import com.aleexalvz.cashwise.data.model.auth.InvalidUserPasswordException
import com.aleexalvz.cashwise.data.model.auth.SignUpInvalidException
import com.aleexalvz.cashwise.data.model.auth.User
import com.aleexalvz.cashwise.data.model.auth.UserNotFoundException
import com.aleexalvz.cashwise.data.source.local.dao.UserDao
import com.aleexalvz.cashwise.data.source.local.model.LocalUser
import com.aleexalvz.cashwise.data.source.local.model.toUser
import com.aleexalvz.cashwise.feature.login.viewmodel.REMEMBER_ME_EMAIL_KEY
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val sharedPreferences: SharedPreferences
) : AuthRepository {

    override suspend fun doLogin(email: String, password: String): User {
        return withContext(IO) {
            try {
                getUserByEmail(email).takeIf { it.password == password }
                    ?: throw InvalidUserPasswordException("Wrong Password")
            } catch (e: InvalidUserPasswordException) {
                throw e
            } catch (e: Exception) {
                throw UserNotFoundException("User not found")
            }
        }
    }

    override suspend fun signupUser(email: String, password: String): User {
        return withContext(IO) {
            try {
                val localUser = LocalUser(email = email, password = password)
                userDao.register(localUser)
                userDao.getByEmail(email = email).toUser()
            } catch (error: Exception) {
                throw SignUpInvalidException("This user data is invalid to sign up")
            }
        }
    }

    private suspend fun getUserByEmail(email: String): User {
        return withContext(IO) {
            userDao.getByEmail(email).toUser()
        }
    }

    override fun verifyRememberUser(): String? =
        sharedPreferences.getString(REMEMBER_ME_EMAIL_KEY, null)

    override fun rememberUser(email: String) {
        sharedPreferences.edit().putString(REMEMBER_ME_EMAIL_KEY, email).apply()
    }

    override fun forgetUser() {
        sharedPreferences.edit().putString(REMEMBER_ME_EMAIL_KEY, null).apply()
    }
}
