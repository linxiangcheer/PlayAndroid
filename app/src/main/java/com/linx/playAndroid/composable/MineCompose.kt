package com.linx.playAndroid.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linx.playAndroid.R

/**
 * 我的页面
 */
@Composable
fun MineCompose() {

    Column(
        modifier = Modifier.background(MaterialTheme.colors.primary).fillMaxSize()
    ) {

        //头像和名字
        HeadAndName()

        Surface(
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize().padding(top = 50.dp)
        ) {

            Text("")

        }

    }

}

/**
 * 头像和名字
 */
@Composable
private fun HeadAndName() {
    Row(
        modifier = Modifier.height(80.dp).fillMaxWidth().padding(start = 20.dp)
    ) {
        //头像
        Surface(
            //圆
            shape = CircleShape,
            modifier = Modifier.size(80.dp)
        ) {
            Image(painterResource(R.mipmap.ic_net_empty), contentDescription = null)
        }

        Column(
            modifier = Modifier.padding(start = 20.dp).weight(1f).fillMaxHeight(),
            //平分
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text("姓名", fontWeight = FontWeight.Bold, fontSize = 20.sp)

            Row {
                Text("id: xxx", fontSize = 14.sp)
                Text("排名: xxx", modifier = Modifier.padding(start = 20.dp), fontSize = 14.sp)
            }
        }
    }
}