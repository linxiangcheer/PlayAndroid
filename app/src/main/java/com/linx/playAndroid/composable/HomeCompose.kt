package com.linx.playAndroid.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

/**
 * 首页页面
 */
@Composable
fun HomeCompose(navController: NavController) {

    Card(
        modifier = Modifier.padding(top = 10.dp, start = 10.dp, end = 10.dp).fillMaxWidth()
            .height(60.dp),
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 2.dp,
        contentColor = MaterialTheme.colors.background
    ) {

        Text("跳转", fontSize = 20.sp, modifier = Modifier.clickable(onClick = {

        }).fillMaxSize(), textAlign = TextAlign.Center)

    }

}