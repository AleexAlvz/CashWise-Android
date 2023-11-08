package com.aleexalvz.cashwise.feature.home.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aleexalvz.cashwise.R
import com.aleexalvz.cashwise.data.model.transaction.TransactionCategory
import com.aleexalvz.cashwise.data.model.transaction.color
import com.aleexalvz.cashwise.ui.theme.CashWiseTheme
import com.aleexalvz.cashwise.ui.theme.GrayDefault
import com.aleexalvz.cashwise.ui.theme.GrayLight

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        uiState = uiState, fetchScreenData = viewModel::fetchScreenData
    )
}

@Composable
fun HomeScreen(
    uiState: HomeUIState, fetchScreenData: () -> Unit
) {

    if (uiState.isFetchedData.not()) fetchScreenData()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            modifier = Modifier.padding(top = 50.dp),
            text = stringResource(R.string.balance),
            color = Color.Green,
            fontSize = 20.sp
        )

        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = uiState.totalBalance,
            color = Color.White,
            fontSize = 34.sp
        )

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
                    text = stringResource(R.string.my_wallet),
                    color = Color.White,
                    fontSize = 18.sp
                )

                Row(
                    modifier = Modifier
                        .padding(top = 47.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    WalletPieChart(
                        modifier = Modifier.size(150.dp),
                        wallet = uiState.wallet
                    )

                    LazyColumn(
                        modifier = Modifier.padding(start = 32.dp, top = 8.dp)
                    ) {
                        uiState.wallet.forEach { (category, _) ->
                            item {
                                Row(modifier = Modifier.padding(bottom = 4.dp)) {
                                    Canvas(modifier = Modifier.size(20.dp)) {
                                        drawCircle(category.color())
                                    }
                                    Text(
                                        modifier = Modifier.padding(start = 9.dp),
                                        maxLines = 1,
                                        text = category.name,
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
}

@Preview
@Composable
fun HomeScreenPreview() {
    CashWiseTheme {
        HomeScreen(uiState = HomeUIState(
            totalBalance = "R$ 12.000,57", wallet = listOf(
                TransactionCategory.SAVINGS to 3000.00,
                TransactionCategory.REAL_STATE to 2000.00,
                TransactionCategory.STOCKS to 6000.00,
                TransactionCategory.OTHERS to 1000.57,
            ), isFetchedData = true
        ), fetchScreenData = {})
    }
}
