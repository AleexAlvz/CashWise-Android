package com.aleexalvz.cashwise.data.model.transaction

import androidx.compose.ui.graphics.Color
import com.aleexalvz.cashwise.ui.theme.GreenPieGraphColor
import com.aleexalvz.cashwise.ui.theme.OceanBluePieGraphColor
import com.aleexalvz.cashwise.ui.theme.OrangePieGraphColor
import com.aleexalvz.cashwise.ui.theme.PurplePieGraphColor
import com.aleexalvz.cashwise.ui.theme.RedPieGraphColor
import com.aleexalvz.cashwise.ui.theme.YellowPieGraphColor

enum class TransactionCategory {
    STOCKS, REAL_STATE, FIXED_INCOME, SAVINGS, TREASURE_BONDS, OTHERS
}

fun getTransactionCategoryByName(name: String): TransactionCategory? {
    TransactionCategory.values().forEach {
        if (it.name == name) return it
    }
    return null
}

fun TransactionCategory.color(): Color = when (this) {
    TransactionCategory.STOCKS -> OrangePieGraphColor
    TransactionCategory.REAL_STATE -> YellowPieGraphColor
    TransactionCategory.FIXED_INCOME -> PurplePieGraphColor
    TransactionCategory.SAVINGS -> RedPieGraphColor
    TransactionCategory.TREASURE_BONDS -> GreenPieGraphColor
    TransactionCategory.OTHERS -> OceanBluePieGraphColor
}
