package com.rrat.clonerallycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.rrat.clonerallycompose.ui.componets.RallyTabRow
import com.rrat.clonerallycompose.ui.screens.AccountsScreen
import com.rrat.clonerallycompose.ui.screens.BillsScreen
import com.rrat.clonerallycompose.ui.screens.OverViewScreen
import com.rrat.clonerallycompose.ui.screens.SingleAccountScreen
import com.rrat.clonerallycompose.ui.theme.CloneRallyComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RallyApp()
        }
    }
}

@Composable
fun RallyApp(){
    CloneRallyComposeTheme {

        val navController = rememberNavController()

        val currentBackStack by navController.currentBackStackEntryAsState()

        val currentDestination = currentBackStack?.destination

        val currentScreen = rallyTabRowsScreen.find{ it.route == currentDestination?.route} ?: Overview

        Scaffold(
            topBar = {
                RallyTabRow(
                    allScreens = rallyTabRowsScreen,
                    onTabSelected = { navController.navigationSingleTopTo(it.route) },
                    currentScreen = currentScreen
                )
            }
        ) {
            innerPadding->
            RallyNavHost(Modifier.padding(innerPadding), navController)
        }
    }
}

@Composable
fun RallyNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Overview.route,
    ) {
        composable(route = Overview.route) {
            OverViewScreen(
                onClickSeeAllAccounts = {
                    navController.navigationSingleTopTo(Accounts.route)
                },
                onClickSeeAllBills = {
                    navController.navigationSingleTopTo(Bills.route)
                },
                onAccountClick = { accountTypeArg ->
                    navController
                        .navigationToSingleAccount(accountTypeArg)
                }
            )
        }
        composable(route = Accounts.route) {
            AccountsScreen(
                onAccountClick = { accountTypeArg ->
                    navController
                        .navigationToSingleAccount(accountTypeArg)
                }
            )
        }
        composable(route = Bills.route) {
            BillsScreen()
        }
        composable(
            route = "${SingleAccount.route}/{${SingleAccount.accountTypeArg}}",
            arguments = SingleAccount.arguments,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "rally://${SingleAccount.route}/{${SingleAccount.accountTypeArg}}"
                }
            )
        ) { navBackStackEntry ->
            val accountType = navBackStackEntry.arguments?.getString(SingleAccount.accountTypeArg)
            SingleAccountScreen(accountType)
        }
    }
}

fun NavHostController.navigationSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(this@navigationSingleTopTo.graph.findStartDestination().id)
        {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

fun NavHostController.navigationToSingleAccount(accountTypeArg: String){
    this.navigationSingleTopTo("${SingleAccount.route}/$accountTypeArg")
}