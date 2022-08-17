package com.rrat.clonerallycompose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.rrat.clonerallycompose.ui.screens.AccountsScreen
import com.rrat.clonerallycompose.ui.screens.BillsScreen
import com.rrat.clonerallycompose.ui.screens.OverViewScreen

/*
*
* */
interface RallyDestination {
    val icon: ImageVector
    val route: String
}


object Overview: RallyDestination{
    override val icon: ImageVector = Icons.Filled.PieChart
    override val route: String = "overview"
}

object Accounts: RallyDestination{
    override val icon: ImageVector = Icons.Filled.AttachMoney
    override val route: String = "accounts"
}

object Bills: RallyDestination{
    override val icon: ImageVector = Icons.Filled.MoneyOff
    override val route: String = "bills"
}

object SingleAccount: RallyDestination{
    override val icon: ImageVector = Icons.Filled.AttachMoney
    override val route: String = "single_account"
    const val accountTypeArg = "account_type"
    val arguments = listOf(
    navArgument(SingleAccount.accountTypeArg){type = NavType.StringType}
    )
}

// TabRow
val rallyTabRowsScreen = listOf(Overview, Accounts, Bills)