package com.aleexalvz.cashwise.feature.home.home.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aleexalvz.cashwise.R
import com.aleexalvz.cashwise.data.model.investment.color
import com.aleexalvz.cashwise.feature.home.home.HomeUIState
import com.aleexalvz.cashwise.feature.home.home.InvestmentsPieChart
import com.aleexalvz.cashwise.ui.theme.GrayDefault
import com.aleexalvz.cashwise.ui.theme.GrayLight

@Composable
fun MyInvestmentsCard(homeUIState: HomeUIState){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(360.dp)
            .padding(top = 28.dp),
        border = BorderStroke(1.dp, GrayLight),
        colors = CardDefaults.cardColors(containerColor = GrayDefault),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 21.dp),
                text = stringResource(R.string.my_investments),
                color = Color.White,
                fontSize = 18.sp
            )

            Row(
                modifier = Modifier
                    .padding(top = 47.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                InvestmentsPieChart(
                    modifier = Modifier.size(150.dp),
                    investments = homeUIState.investments
                )

                LazyColumn(
                    modifier = Modifier.padding(start = 32.dp, top = 8.dp)
                ) {
                    homeUIState.investments.forEach { (category, _) ->
                        item {
                            Row(modifier = Modifier.padding(bottom = 4.dp)) {
                                Canvas(modifier = Modifier.size(20.dp)) {
                                    drawCircle(category.color())
                                }
                                Text(
                                    modifier = Modifier.padding(start = 9.dp),
                                    maxLines = 1,
                                    text = category.title,
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}