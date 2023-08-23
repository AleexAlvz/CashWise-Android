package com.aleexalvz.cashwise.helper

import android.app.Application
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object JsonHelper {

    private fun getJsonDataFromAsset(fileName: String, application: Application): String? {
        return try {
            application.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    fun <T> getListDataFromAsset(application: Application, fileName: String): List<T> {
        val jsonData = getJsonDataFromAsset(fileName, application)
        val usersDataType = object : TypeToken<List<T>>() {}.type
        return Gson().fromJson(jsonData, usersDataType)
    }
}