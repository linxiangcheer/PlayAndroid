package com.linx.playAndroid.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.linx.playAndroid.KeyNavigationRoute

/**
 * 学习页面
 */
@Composable
fun LearnCompose(navHostController: NavHostController) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            ListItemScreen(navHostController, "动画")
        }
    }
}

/**
 * 只有一个按钮的控件
 */
@Composable
private fun ListItemScreen(navHostController: NavHostController, str: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                navHostController.navigate(KeyNavigationRoute.LEARN_ANIMATION.route)
            },
            modifier = Modifier
                .padding(top = 30.dp, bottom = 30.dp)
                .size(200.dp, 40.dp)
        ) {
            Text(
                text = str,
                fontSize = 15.sp
            )
        }
    }
    Divider()
}