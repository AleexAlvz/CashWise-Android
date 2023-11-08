package com.aleexalvz.cashwise.feature.home.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aleexalvz.cashwise.R
import com.aleexalvz.cashwise.ui.theme.CashWiseTheme

@Composable
fun HomeScreen() {

    //Preparar lógica no momento de add ou edit transacoes, dentro de um use case. Ao mudar a transacao,
    // salvar em outra tabela o saldo do usuario atualizado
    // Na tabela de transacoes, colocar referencia de usuario na tabela tbm, para diferenciar entradas de cada usuario
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
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    CashWiseTheme {
        HomeScreen()
    }
}
