package com.rrat.clonerallycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.rrat.clonerallycompose.ui.componets.RallyTabRow
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
        var currentScreen: RallyDestination by remember{ mutableStateOf(Overview)}

        Scaffold(
            topBar = {
                RallyTabRow(
                    allScreens = rallyTabRowsScreen,
                    onTabSelected = { currentScreen = it},
                    currentScreen = currentScreen
                )
            }
        ) {
            innerPadding->
            Box(Modifier.padding(innerPadding)){
                currentScreen.screen()
            }
        }
    }
}
