package com.aleexalvz.cashwise.helper

import android.content.Context
import com.google.gson.Gson
import java.lang.reflect.Type

object JsonHelper {

    private fun getJsonDataFromAsset(fileName: String, context: Context): String? {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    fun <T> getListDataFromAsset(context: Context, fileName: String, typeToken: Type): T {
        val jsonData = getJsonDataFromAsset(fileName, context)
        return Gson().fromJson(jsonData, typeToken)
    }
}