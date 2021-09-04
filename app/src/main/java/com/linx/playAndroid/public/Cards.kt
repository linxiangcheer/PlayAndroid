package com.linx.playAndroid.public

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * 首页采用的Card
 * 卡片高度可以设置
 */
@Composable
fun SimpleCard(
    cardHeight: Dp = 120.dp,
    elevation: Dp = 3.dp,
    shape: RoundedCornerShape = RoundedCornerShape(6.dp),
    content: @Composable () -> Unit
) {

    Card(
        modifier = Modifier
            //外边距
            .padding(bottom = 5.dp, start = 8.dp, end = 8.dp, top = 5.dp).fillMaxWidth()
            .height(cardHeight),
        elevation = elevation,
        backgroundColor = MaterialTheme.colors.background,
        shape = shape,
        border = if (isSystemInDarkTheme()) BorderStroke(1.dp, Color.White) else null
    ) {
        content()
    }

}

/**
 * 卡片高度自适应
 */
@Composable
fun SimpleCard(
    elevation: Dp = 3.dp,
    shape: RoundedCornerShape = RoundedCornerShape(6.dp),
    content: @Composable () -> Unit
) {

    Card(
        modifier = Modifier
            //外边距
            .padding(bottom = 5.dp, start = 8.dp, end = 8.dp, top = 5.dp).fillMaxWidth(),
        elevation = elevation,
        backgroundColor = MaterialTheme.colors.background,
        shape = shape,
        border = if (isSystemInDarkTheme()) BorderStroke(1.dp, Color.White) else null
    ) {
        content()
    }

}