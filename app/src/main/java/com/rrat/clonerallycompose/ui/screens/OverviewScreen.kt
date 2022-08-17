package com.rrat.clonerallycompose.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.rrat.clonerallycompose.R
import com.rrat.clonerallycompose.data.UserData
import com.rrat.clonerallycompose.ui.componets.OverviewScreenCard


@Composable
fun OverViewScreen(
    onClickSeeAllAccounts: () -> Unit = {},
    onClickSeeAllBills: () -> Unit = {},
    onAccountClick: (String) -> Unit = {},
){

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .semantics { contentDescription="Overview Screen" }
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        AccountsCard(
            onClickSeeAll = onClickSeeAllAccounts,
            onAccountClick = onAccountClick
        )
        Spacer(modifier = Modifier.height(12.dp))
        BillsCard(
            onClickSeeAll = onClickSeeAllBills
        )
    }

}

@Composable
private fun AccountsCard(onClickSeeAll: () -> Unit, onAccountClick: (String) -> Unit) {
    val amount = UserData.accounts.map { account -> account.balance }.sum()
    OverviewScreenCard(
        title = stringResource(R.string.accounts),
        amount = amount,
        onClickSeeAll = onClickSeeAll,
        data = UserData.accounts,
        colors = { it.color },
        values = { it.balance }
    ) { account ->
        AccountRow(
            modifier = Modifier.clickable { onAccountClick(account.name) },
            name = account.name,
            number = account.number,
            amount = account.balance,
            color = account.color
        )
    }
}

@Composable
private fun BillsCard(onClickSeeAll: () -> Unit) {
    val amount = UserData.bills.map { bill -> bill.amount }.sum()
    OverviewScreenCard(
        title = stringResource(R.string.bills),
        amount = amount,
        onClickSeeAll = onClickSeeAll,
        data = UserData.bills,
        colors = { it.color },
        values = { it.amount }
    ) { bill ->
        BillRow(
            name = bill.name,
            due = bill.due,
            amount = bill.amount,
            color = bill.color
        )
    }
}