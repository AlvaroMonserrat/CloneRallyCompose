package com.rrat.clonerallycompose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.rrat.clonerallycompose.ui.screens.AccountsScreen
import com.rrat.clonerallycompose.ui.screens.BillsScreen
import com.rrat.clonerallycompose.ui.screens.OverViewScreen

/*
*
* */
interface RallyDestination {
    val icon: ImageVector
    val route: String
    val screen: @Composable ()->Unit
}

object Overview: RallyDestination{
    override val icon: ImageVector = Icons.Filled.PieChart
    override val route: String = "overview"
    override val screen: @Composable () -> Unit = { OverViewScreen()}
}

object Accounts: RallyDestination{
    override val icon: ImageVector = Icons.Filled.AttachMoney
    override val route: String = "accounts"
    override val screen: @Composable () -> Unit = { AccountsScreen()}
}

object Bills: RallyDestination{
    override val icon: ImageVector = Icons.Filled.MoneyOff
    override val route: String = "bills"
    override val screen: @Composable () -> Unit = { BillsScreen()}
}

// TabRow
val rallyTabRowsScreen = listOf(Overview, Accounts, Bills)