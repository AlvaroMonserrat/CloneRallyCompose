package com.rrat.clonerallycompose.ui.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.dp
import com.rrat.clonerallycompose.R
import java.text.DecimalFormat


@Composable
fun <T> OverviewScreenCard(
    title: String,
    amount: Float,
    onClickSeeAll: () ->Unit,
    values: (T) -> Float,
    colors: (T) -> Color,
    data: List<T>,
    row: @Composable (T) -> Unit
){

    Card() {
        Column() {
            Column(
                Modifier.padding(12.dp)
            ) {
                Text(text = title, style = MaterialTheme.typography.subtitle2)
                val amountText = "$" + DecimalFormat("####").format(amount)
                Text(text = amountText, style = MaterialTheme.typography.h2)
                OverViewDivider(data = data, values = values, colors = colors)

                Column(Modifier.padding(start = 16.dp, top = 4.dp, end = 8.dp)) {
                    data.take(3).forEach {
                        row(it)
                    }
                    SeeAllButton(
                        modifier = Modifier.clearAndSetSemantics {
                            contentDescription = "All $title"
                        },
                        onClick = onClickSeeAll
                    )
                }
            }
        }
    }
}

@Composable
private fun <T> OverViewDivider(
    data: List<T>,
    values: (T) -> Float,
    colors: (T) -> Color
) {
    Row(Modifier.fillMaxWidth()) {
        data.forEach { item: T ->
            Spacer(
                modifier = Modifier
                    .weight(values(item))
                    .height(1.dp)
                    .background(colors(item))
            )
        }
    }
}

@Composable
private fun SeeAllButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = modifier
            .height(44.dp)
            .fillMaxWidth()
    ) {
        Text(stringResource(R.string.see_all))
    }
}