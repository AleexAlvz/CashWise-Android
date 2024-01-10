package com.aleexalvz.cashwise.data.model.investment

import androidx.compose.ui.graphics.Color
import com.aleexalvz.cashwise.ui.theme.GreenPieGraphColor
import com.aleexalvz.cashwise.ui.theme.OceanBluePieGraphColor
import com.aleexalvz.cashwise.ui.theme.OrangePieGraphColor
import com.aleexalvz.cashwise.ui.theme.PurplePieGraphColor
import com.aleexalvz.cashwise.ui.theme.RedPieGraphColor
import com.aleexalvz.cashwise.ui.theme.YellowPieGraphColor

enum class InvestmentCategory(val title: String) {
    STOCKS("Stocks"),
    REAL_STATE("Real State"),
    FIXED_INCOME("Fixed Income"),
    SAVINGS("Savings"),
    TREASURE_BONDS("Treasure Bonds"),
    OTHERS("Others")
}

fun getInvestmentCategoryByTitle(title: String): InvestmentCategory? {
    InvestmentCategory.values().forEach {
        if (it.title == title) return it
    }
    return null
}

fun InvestmentCategory.color(): Color = when (this) {
    InvestmentCategory.STOCKS -> OrangePieGraphColor
    InvestmentCategory.REAL_STATE -> YellowPieGraphColor
    InvestmentCategory.FIXED_INCOME -> PurplePieGraphColor
    InvestmentCategory.SAVINGS -> RedPieGraphColor
    InvestmentCategory.TREASURE_BONDS -> GreenPieGraphColor
    InvestmentCategory.OTHERS -> OceanBluePieGraphColor
}
