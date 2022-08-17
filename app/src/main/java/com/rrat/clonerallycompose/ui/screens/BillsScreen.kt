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
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.rrat.clonerallycompose.R
import com.rrat.clonerallycompose.data.Bill
import com.rrat.clonerallycompose.data.UserData
import com.rrat.clonerallycompose.ui.componets.BaseRow
import com.rrat.clonerallycompose.ui.componets.StatementBody
import java.text.DecimalFormat

@Composable
fun BillsScreen(
    bills: List<Bill> = remember {
        UserData.bills
    }
){
    val amountsTotal = remember{ bills.map{ bills -> bills.amount }.sum()}

    StatementBody(
        modifier = Modifier.clearAndSetSemantics { contentDescription = "Bills Screen" },
        items = bills,
        colors = {bill ->  bill.color},
        amounts = {bill ->  bill.amount},
        amountsTotal = amountsTotal,
        circleLabel = stringResource(id = R.string.due),
        rows = {
                bill ->
            BillRow(
                name = bill.name,
                due = bill.due,
                amount = bill.amount,
                color = bill.color
            )
        }
    )
}

@Composable
fun BillRow(
    modifier: Modifier=Modifier,
    name: String,
    due: String,
    amount: Float,
    color: Color
){
    BaseRow(
        modifier=modifier,
        color = color,
        title = name,
        subtitle = "Due $due",
        amount = amount,
        negative = true)
}