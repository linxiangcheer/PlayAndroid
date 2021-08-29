package com.linx.playAndroid.public.paging

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.linx.common.R

/**
 * 加载失败页面
 */
@Composable
fun ErrorPaging(block: () -> Unit) {

    Column(
        modifier = Modifier.background(Color.White).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(300.dp, 180.dp),
            painter = painterResource(id = R.mipmap.ic_net_empty),
            contentDescription = "网络问题",
            contentScale = ContentScale.Crop
        )

        Button(modifier = Modifier.padding(8.dp), onClick = block) {
            Text("网络不佳，请点击重试")
        }
    }

}