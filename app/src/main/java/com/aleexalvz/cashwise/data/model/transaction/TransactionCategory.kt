package com.aleexalvz.cashwise.data.model.transaction

import androidx.compose.ui.graphics.Color
import com.aleexalvz.cashwise.ui.theme.GreenPieGraphColor
import com.aleexalvz.cashwise.ui.theme.OceanBluePieGraphColor
import com.aleexalvz.cashwise.ui.theme.OrangePieGraphColor
import com.aleexalvz.cashwise.ui.theme.PurplePieGraphColor
import com.aleexalvz.cashwise.ui.theme.RedPieGraphColor
import com.aleexalvz.cashwise.ui.theme.YellowPieGraphColor

enum class TransactionCategory(val title: String) {
    STOCKS("Stocks"),
    REAL_STATE("Real State"),
    FIXED_INCOME("Fixed Income"),
    SAVINGS("Savings"),
    TREASURE_BONDS("Treasure Bonds"),
    OTHERS("Others")
}

fun getTransactionCategoryByTitle(title: String): TransactionCategory? {
    TransactionCategory.values().forEach {
        if (it.title == title) return it
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
