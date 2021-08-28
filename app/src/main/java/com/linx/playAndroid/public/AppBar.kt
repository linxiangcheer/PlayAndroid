package com.linx.playAndroid.public

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * 标题栏
 */
@Composable
fun AppBar(
    title: String = "",
    leftIcon: ImageVector? = null,
    rightIcon: ImageVector? = null,
    onLeftClick: () -> Unit = {},
    onRightClick: () -> Unit = {}
) {

    if (title == "") {
        return
    }

    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary
    ) {

        Box(
            contentAlignment = Alignment.Center
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                //左边图标
                if (leftIcon == null)
                    Spacer(modifier = Modifier.size(10.dp))
                else
                    Icon(leftIcon, contentDescription = null, modifier = Modifier.clickable {
                        onLeftClick()
                    }.padding(start = 10.dp), tint = MaterialTheme.colors.background)

                //右边图标
                rightIcon?.let {
                    Icon(rightIcon, contentDescription = null, Modifier.clickable {
                        onRightClick()
                    }.padding(end = 10.dp), tint = MaterialTheme.colors.background)
                }
            }

            //标题文字
            Text(
                text = title,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                color = MaterialTheme.colors.background
            )
        }


    }

}