package com.linx.playAndroid.public

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

/**
 * 两个Text上下分布的布局
 */
@Composable
fun ColumnTextText(
    topStr: String,
    bottomStr: String,
    modifier: Modifier = Modifier,
    context: @Composable () -> Unit = {}
) {

    Row(
        modifier = Modifier.background(MaterialTheme.colors.background).then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier.weight(1f, true)
        ) {
            Text(topStr, color = MaterialTheme.colors.secondaryVariant, fontSize = 18.sp)
            Text(bottomStr, color = Color.Gray, fontSize = 14.sp)
        }

        context()

    }

}