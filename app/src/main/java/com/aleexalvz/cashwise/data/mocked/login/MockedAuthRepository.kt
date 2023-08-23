package com.aleexalvz.cashwise.data.mocked.login

import android.app.Application
import android.util.Log
import com.aleexalvz.cashwise.data.model.User
import com.aleexalvz.cashwise.helper.JsonHelper
import com.aleexalvz.cashwise.data.repository.AuthRepository

class MockedAuthRepository(private val application: Application) : AuthRepository {

    companion object {
        private var loggedUser: User? = null
    }

    private fun getUserByEmail(application: Application, email: String): User? {
        return getAllUsers(application).firstOrNull { it.email == email }
    }

    private fun getAllUsers(application: Application): List<User> {
        JsonHelper.getListDataFromAsset<User>(application, "usersmocked.json").apply {
            Log.i("Json request", this.toString())
            return this
        }
    }

    override fun signupUser(email: String, password: String): Boolean {
        loggedUser = User(email = email)
        return true
    }

    override fun doLogin(email: String, password: String): Boolean {
        val user = getUserByEmail(application, email)
        user?.let {
            loggedUser = it
            return true
        }
        return false
    }
}

fun Application.getMockedAuthRepository() = MockedAuthRepository(this)
