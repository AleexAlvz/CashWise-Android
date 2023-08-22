package com.aleexalvz.cashwise.data.mocked

import android.content.Context
import android.util.Log
import com.aleexalvz.cashwise.data.model.User

class MockedLoginRepository {

    companion object {
        private var loggedUser: User? = null
    }

    private fun getUserByEmail(context: Context, email: String): User? {
        return getAllUsers(context).firstOrNull { it.email == email }
    }

    private fun getAllUsers(context: Context): List<User> {
        JsonUtils.getListDataFromAsset<User>(context, "usersmocked.json").apply {
            Log.i("Json request", this.toString())
            return this
        }
    }

    fun doLogin(context: Context, email: String, password: String): Boolean {
        val user = getUserByEmail(context, email)
        return true
    }
}