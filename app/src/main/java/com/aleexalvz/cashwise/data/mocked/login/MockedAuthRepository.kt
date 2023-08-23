package com.aleexalvz.cashwise.data.mocked.login

import android.app.Application
import android.util.Log
import com.aleexalvz.cashwise.data.model.User
import com.aleexalvz.cashwise.data.repository.AuthRepository
import com.aleexalvz.cashwise.helper.JsonHelper
import com.aleexalvz.cashwise.model.SignUpInvalidException
import com.aleexalvz.cashwise.model.UserNotFoundException
import com.google.gson.reflect.TypeToken

class MockedAuthRepository(private val application: Application) : AuthRepository {

    private fun getUserByEmail(application: Application, email: String): User {
        return getAllUsers(application).firstOrNull { it.email == email }!!
    }

    private fun getAllUsers(application: Application): List<User> {
        val typeToken = object : TypeToken<List<User>>() {}.type
        JsonHelper.getListDataFromAsset<List<User>>(
            application,
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

    override fun doLogin(email: String, password: String): User {
        try {
            return getUserByEmail(application, email)
        } catch (e: Exception) {
            throw UserNotFoundException("User not found")
        }
    }
}

fun Application.getMockedAuthRepository() = MockedAuthRepository(this)
