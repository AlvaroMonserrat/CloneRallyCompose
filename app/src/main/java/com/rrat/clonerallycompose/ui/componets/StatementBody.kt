package com.rrat.clonerallycompose.ui.componets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun <T> StatementBody(
    modifier: Modifier = Modifier,
    items: List<T>,
    colors: (T) -> Color,
    amounts: (T) -> Float,
    amountsTotal: Float,
    circleLabel: String,
    rows: @Composable (T) ->Unit
){
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        Box(modifier = Modifier.padding(16.dp)){

            //val accountsProportion = items.extra

            //val circleColors = items.map{ colors(it)}

            Spacer(modifier = Modifier.height(10.dp))
            Card() {
                Column(modifier=Modifier.padding(12.dp)) {
                    items.forEach{
                        item->rows(item)
                    }
                }
            }

        }
    }
}