package com.rrat.clonerallycompose.ui.componets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rrat.clonerallycompose.Overview
import com.rrat.clonerallycompose.RallyDestination
import com.rrat.clonerallycompose.rallyTabRowsScreen
import java.util.*


@Composable
fun RallyTabRow(
    modifier: Modifier = Modifier,
    allScreens: List<RallyDestination>,
    onTabSelected: (RallyDestination)->Unit,
    currentScreen: RallyDestination
){
    Surface(
        modifier= Modifier
            .height(TabHeight)
            .fillMaxWidth()
    ){
        Row(modifier = modifier.selectableGroup()) {
            allScreens.forEach{
                screen->
                RallyTab(
                    titleTab = screen.route,
                    icon = screen.icon,
                    onSelected = {onTabSelected(screen)},
                    selected = currentScreen == screen
                )
            }
        }
    }

}

@Composable
fun RallyTab(
    modifier: Modifier=Modifier,
    titleTab: String,
    icon: ImageVector,
    onSelected: () -> Unit,
    selected: Boolean
){
    Row(
        modifier = Modifier
            .padding(16.dp)
            .height(TabHeight)
            .clearAndSetSemantics { contentDescription = titleTab }
            .selectable(
                selected = selected,
                onClick = onSelected,
                role = Role.Tab
            )
    ) {
        Icon(imageVector = icon, contentDescription = titleTab)
        if(selected){
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = titleTab.uppercase(Locale.getDefault()))
        }
    }
}

private val TabHeight = 56.dp


@Preview(showBackground = true)
@Composable
fun RallyTabRowPreview(){
    RallyTabRow(allScreens=rallyTabRowsScreen, onTabSelected = {}, currentScreen = Overview)
}