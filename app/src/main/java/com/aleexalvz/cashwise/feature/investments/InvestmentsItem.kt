package com.aleexalvz.cashwise.feature.investments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingDown
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aleexalvz.cashwise.data.model.statement.Statement
import com.aleexalvz.cashwise.data.model.investment.InvestmentCategory
import com.aleexalvz.cashwise.data.model.investment.InvestmentType
import com.aleexalvz.cashwise.ui.theme.CashWiseTheme
import com.aleexalvz.cashwise.ui.theme.DarkBlueBackground
import com.aleexalvz.cashwise.ui.theme.GrayLight
import com.aleexalvz.cashwise.ui.theme.GraySecondaryText
import com.aleexalvz.cashwise.ui.theme.Green
import com.aleexalvz.cashwise.ui.theme.Red

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvestmentsItem(
    statement: Statement,
    onClickItem: (Long) -> Unit
) {
    OutlinedCard(
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.outlinedCardColors(contentColor = GrayLight),
        onClick = {
            onClickItem(statement.id)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier
                    .height(60.dp)
                    .width(50.dp),
                shape = RoundedCornerShape(corner = CornerSize(8.dp)),
                colors = CardDefaults.cardColors(containerColor = DarkBlueBackground)
            ) {
                val (icon, color) = when (statement.type) {
                    InvestmentType.PROFIT -> Pair(Icons.AutoMirrored.Filled.TrendingUp, Green)
                    InvestmentType.LOSS -> Pair(Icons.AutoMirrored.Filled.TrendingDown, Red)
                }

                Icon(
                    imageVector = icon,
                    contentDescription = "Statement Icon",
                    tint = color,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                )
            }

            Column(
                modifier = Modifier
                    .padding(start = 14.dp)
                    .wrapContentSize(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    modifier = Modifier.fillMaxSize(0.5f),
                    text = statement.title,
                    color = Color.White,
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.padding(2.dp))

                Text(
                    text = statement.category.title,
                    color = GraySecondaryText,
                    fontSize = 14.sp
                )
            }

            Column(
                modifier = Modifier
                    .padding(end = 17.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = statement.totalValue,
                    color = Color.White,
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.padding(2.dp))

                Text(
                    text = statement.date,
                    color = GraySecondaryText,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InvestmentsItemPreview() {
    CashWiseTheme {
        Column(Modifier.background(Color.DarkGray)) {
            InvestmentsItem(
                Statement(
                    id = 0,
                    title = "Title sample for preview",
                    category = InvestmentCategory.SAVINGS,
                    totalValue = "R$ 15000,00",
                    type = InvestmentType.PROFIT,
                    date = "19/10/2022"
                )
            ) {}
        }
    }
}