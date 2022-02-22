package com.linx.playAndroid.public

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linx.playAndroid.ui.theme.Typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * 标题
 */
@Composable
fun TitleText(title: String, modifier: Modifier = Modifier) {
    Text(text = title, modifier = modifier.padding(8.dp), style = Typography.h3.copy(fontSize = 14.sp))
}

/**
 * 副标题
 */
@Composable
fun SubtitleText(subtitle: String, modifier: Modifier = Modifier) {
    Text(text = subtitle, style = Typography.subtitle2, modifier = modifier.padding(8.dp))
}

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

/**
 * Text+Icon
 * 分别在最左边和最右边
 */
@Composable
fun RowTextIcon(
    //text文本
    text: String,
    //Icon图标
    imageVector: ImageVector = Icons.Default.Close,
    paddingTop: Dp = 0.dp,
    //图标点击事件
    imageClick: (CoroutineScope.() -> Unit)? = null
) {

    var index by remember { mutableStateOf(0) }

    LaunchedEffect(index) {

        if (index == 0) return@LaunchedEffect

        launch(Dispatchers.IO) {
            if (imageClick != null) imageClick()
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth().padding(top = paddingTop).height(30.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(text, fontSize = 16.sp, color = Color.Gray)

        Icon(
            imageVector, contentDescription = null, modifier = Modifier.clickable(
                onClick = {
                    imageClick?.let { index++ }
                }, indication = null,
                interactionSource = MutableInteractionSource()
            ).padding(5.dp), tint = Color.Gray
        )

    }

}