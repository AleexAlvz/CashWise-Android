package com.aleexalvz.cashwise.data.source.local.repository

import android.content.SharedPreferences
import com.aleexalvz.cashwise.data.model.auth.InvalidUserPasswordException
import com.aleexalvz.cashwise.data.model.auth.SignUpInvalidException
import com.aleexalvz.cashwise.data.model.auth.User
import com.aleexalvz.cashwise.data.model.auth.UserNotFoundException
import com.aleexalvz.cashwise.data.repository.AuthRepository
import com.aleexalvz.cashwise.data.source.local.dao.UserDao
import com.aleexalvz.cashwise.data.source.local.model.LocalUser
import com.aleexalvz.cashwise.data.source.local.model.toUser
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalAuthRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val sharedPreferences: SharedPreferences
) : AuthRepository {

    companion object {
        private const val REMEMBER_ME_EMAIL_KEY = "REMEMBER_ME_LOGIN_KEY"
    }

    override suspend fun doLogin(email: String, password: String): Result<User> = runCatching {
        withContext(IO) {
            val user: User = getUserByEmail(email)
                ?: throw UserNotFoundException("User not found")
            return@withContext user.takeIf { it.password == password }
                ?: throw InvalidUserPasswordException("Wrong Password")
        }
    }

    override suspend fun signupUser(email: String, password: String): Result<User> = runCatching {
        withContext(IO) {
            val localUser = LocalUser(email = email, password = password)
            userDao.register(localUser)
            return@withContext userDao.getByEmail(email = email)?.toUser()
                ?: throw SignUpInvalidException("This user data is invalid to sign up")
        }
    }

    private suspend fun getUserByEmail(email: String): User? {
        return withContext(IO) {
            userDao.getByEmail(email)?.toUser()
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
