package com.aleexalvz.cashwise.helper

import java.text.NumberFormat

fun Double.toCurrencyString(): String {
    val format = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 2
    format.minimumFractionDigits = 2
    return format.format(this)
}