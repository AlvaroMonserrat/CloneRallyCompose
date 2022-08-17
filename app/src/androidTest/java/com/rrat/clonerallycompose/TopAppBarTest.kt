package com.rrat.clonerallycompose

import androidx.compose.material.TopAppBar
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.rrat.clonerallycompose.data.UserData
import com.rrat.clonerallycompose.ui.componets.RallyTabRow
import org.junit.Rule
import org.junit.Test


class TopAppBarTest(){

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun rallyTopAppBarTest_currentTabSelected(){
        val allScreens = rallyTabRowsScreen
        composeTestRule.setContent {
            RallyTabRow(
                allScreens = allScreens,
                onTabSelected = {},
                currentScreen = rallyTabRowsScreen[1]
            )
        }
        composeTestRule
            .onNodeWithContentDescription(rallyTabRowsScreen[1].route)
            .assertIsSelected()
    }

    @Test
    fun rallyTopAppBarTest_currentLabelExists(){
        val allScreens = rallyTabRowsScreen
        composeTestRule.setContent {
            RallyTabRow(
                allScreens = allScreens,
                onTabSelected = {},
                currentScreen = rallyTabRowsScreen[1]
            )
        }
        composeTestRule.onRoot().printToLog("currentLabelExists")
        composeTestRule
            .onNodeWithContentDescription(rallyTabRowsScreen[1].route)
            .assertExists()
    }
}