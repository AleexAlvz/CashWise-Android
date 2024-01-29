package com.aleexalvz.cashwise.helper

import java.util.Currency

fun String.toCurrencyLong(): Long = replace(Regex("[^0-9]"), "").toLong()

fun Long.toCurrencyString(): String {
    val longCurrency = this
    val totalValue: String = buildString {
        //Put zeros if shorter than 1 unit
        if (length < 2) append("00") else if (length < 3) append("0")

        //append long value
        append(longCurrency)

        //Remove zeros at left if bigger than 1 unit
        while (length>3 && first() == '0'){
            deleteCharAt(0)
        }
    }

    val decimal = "${totalValue[totalValue.lastIndex - 1]}${totalValue[totalValue.lastIndex]}"
    val integer = totalValue.removeSuffix(decimal)
    return "$integer.$decimal"
}

fun getCurrencySymbol() = Currency.getInstance("BRL").symbol
