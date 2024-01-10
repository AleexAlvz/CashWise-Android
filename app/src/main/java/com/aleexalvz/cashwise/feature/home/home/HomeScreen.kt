package com.aleexalvz.cashwise.feature.home.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aleexalvz.cashwise.R
import com.aleexalvz.cashwise.data.model.investment.InvestmentCategory
import com.aleexalvz.cashwise.feature.home.home.cards.MyInvestmentsCard
import com.aleexalvz.cashwise.ui.theme.CashWiseTheme

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
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.home_background_img),
            contentDescription = "home background",
            contentScale = ContentScale.FillWidth
        )
    }
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

        MyInvestmentsCard(uiState)
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    CashWiseTheme {
        HomeScreen(uiState = HomeUIState(
            totalBalance = "R$ 12.000,57", investments = listOf(
                InvestmentCategory.SAVINGS to 3000.00,
                InvestmentCategory.REAL_STATE to 2000.00,
                InvestmentCategory.STOCKS to 6000.00,
                InvestmentCategory.OTHERS to 1000.57,
            ), isFetchedData = true
        ), fetchScreenData = {})
    }
}
