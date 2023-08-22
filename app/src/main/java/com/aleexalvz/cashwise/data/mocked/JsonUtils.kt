package com.aleexalvz.cashwise.data.mocked

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object JsonUtils {

    fun getJsonDataFromAsset(fileName: String, context: Context): String? {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    fun <T> getListDataFromAsset(context: Context, fileName: String): List<T> {
        val jsonData = JsonUtils.getJsonDataFromAsset(fileName, context)
        val usersDataType = object : TypeToken<List<T>>() {}.type
        return Gson().fromJson(jsonData, usersDataType)
    }
}