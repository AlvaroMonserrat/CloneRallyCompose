package com.rrat.clonerallycompose.ui.componets

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.dp
import java.text.DecimalFormat


@Composable
fun BaseRow(
    modifier: Modifier = Modifier,
    color: Color,
    title: String,
    subtitle: String,
    amount: Float,
    negative: Boolean
){
    val dollarSing = if(negative) "-$ " else "$ "
    val formattedAmount = DecimalFormat("####").format(amount)

    Row(
        modifier = modifier
            .height(68.dp)
            .clearAndSetSemantics {
                contentDescription =
                    "$title account ending in ${subtitle.takeLast(4)}," +
                            " current balance $dollarSing$formattedAmount"
            },
        verticalAlignment = Alignment.CenterVertically
    ){
        val typography = MaterialTheme.typography
        RowIndicator(color = color)
        Spacer(modifier = Modifier.width(12.dp))
        Column(Modifier) {
            Text(text = title, style = typography.body1)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = subtitle, style = typography.subtitle1)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = dollarSing,
                style = typography.h6,
                modifier = Modifier.align(Alignment.CenterVertically)
                )
            Text(
                text = formattedAmount,
                style = typography.h6,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Icon(
                imageVector = Icons.Filled.ChevronRight,
                contentDescription = null,
                modifier = Modifier.padding(end = 12.dp).size(24.dp)
            )
        }
    }
    Divider(color = MaterialTheme.colors.background, thickness = 1.dp, modifier = modifier)
}

fun <E> List<E>.extractProportions(selector: (E) -> Float): List<Float> {
    val total = this.sumOf { selector(it).toDouble() }
    return this.map { (selector(it) / total).toFloat() }
}