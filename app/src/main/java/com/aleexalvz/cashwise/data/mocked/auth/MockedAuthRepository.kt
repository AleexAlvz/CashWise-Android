package com.aleexalvz.cashwise.data.mocked.auth

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.aleexalvz.cashwise.data.model.auth.SignUpInvalidException
import com.aleexalvz.cashwise.data.model.auth.User
import com.aleexalvz.cashwise.data.model.auth.UserNotFoundException
import com.aleexalvz.cashwise.data.repository.AuthRepository
import com.aleexalvz.cashwise.feature.login.viewmodel.REMEMBER_ME_EMAIL_KEY
import com.aleexalvz.cashwise.helper.JsonHelper
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class MockedAuthRepository @Inject constructor(
    private val context: Context,
    private val sharedPreferences: SharedPreferences
) : AuthRepository {

    private fun getUserByEmail(context: Context, email: String): User {
        return getAllUsers(context = context).firstOrNull { it.email == email }!!
    }

    private fun getAllUsers(context: Context): List<User> {
        val typeToken = object : TypeToken<List<User>>() {}.type
        JsonHelper.getListDataFromAsset<List<User>>(
            context,
            "usersmocked.json",
            typeToken
        ).apply {
            Log.i("Json request", this.toString())
            return this
        }
    }

    override fun signupUser(email: String, password: String): User {
        try {
            return User(email = email)
        } catch (e: Exception) {
            throw SignUpInvalidException("This user data is invalid to sign up")
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

    override fun doLogin(email: String, password: String): User {
        try {
            return getUserByEmail(context, email)
        } catch (e: Exception) {
            throw UserNotFoundException("User not found")
        }
    }
}
