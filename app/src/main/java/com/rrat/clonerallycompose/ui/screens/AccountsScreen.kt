package com.rrat.clonerallycompose.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.rrat.clonerallycompose.R
import com.rrat.clonerallycompose.data.UserData
import com.rrat.clonerallycompose.ui.componets.BaseRow
import com.rrat.clonerallycompose.ui.componets.StatementBody
import java.text.DecimalFormat


@Composable
fun AccountsScreen(){

    val amountsTotal = remember{ UserData.accounts.map{ account -> account.balance }.sum()}
    
    StatementBody(
        modifier = Modifier.semantics { contentDescription = "Accounts Screen" },
        items = UserData.accounts,
        colors = {account ->  account.color},
        amounts = {account ->  account.balance},
        amountsTotal = amountsTotal,
        circleLabel = stringResource(id = R.string.total),
        rows = {
            account ->
            AccountRow(
                modifier = Modifier.clickable {

                },
                name = account.name,
                number = account.number,
                amount = account.balance,
                color = account.color
            )
        }
    )
}

@Composable
fun AccountRow(
    modifier: Modifier=Modifier,
    name: String,
    number: Int,
    amount: Float,
    color: Color
){
    BaseRow(
        modifier=modifier,
        color = color,
        title = name,
        subtitle = stringResource(id = R.string.account_redacted) +  DecimalFormat("####").format(number),
        amount = amount,
        negative = false)
}