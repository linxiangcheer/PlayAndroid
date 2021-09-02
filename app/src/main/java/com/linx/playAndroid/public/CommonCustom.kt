package com.linx.playAndroid.public

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter

/**
 * Card顶部部分
 */
@Composable
fun TopCard(author: String, desc: String) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = author,
            style = MaterialTheme.typography.body1,
            color = Color.Gray
        )
        Text(
            text = desc,
            style = MaterialTheme.typography.body1,
            color = Color.Gray,
            modifier = Modifier.padding(start = 10.dp)
        )
    }

}

/**
 * Card中间部分
 */
@Composable
fun CenterCard(envelopePic: String, title: String, desc: String) {

    val painter = rememberImagePainter(data = envelopePic)

    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxHeight().width(130.dp)
            .padding(end = 4.dp)
    )

    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            maxLines = 2,
            fontSize = 17.sp,
            //超长以...结尾
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = desc,
            style = MaterialTheme.typography.body1,
            color = Color.Gray,
            modifier = Modifier.padding(top = 3.dp),
            maxLines = 4,
            fontSize = 14.sp,
            //超长以...结尾
            overflow = TextOverflow.Ellipsis
        )
    }
}

/**
 * Card底部部分
 */
@Composable
fun BottomCard(
    //渠道名
    chapterName: String,
    //是否收藏
    collect: Boolean,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(chapterName, color = Color.Gray, fontSize = 12.sp)
        //收藏
        Icon(
            Icons.Default.Favorite,
            contentDescription = null,
            tint = if (collect) Color.Red else Color.LightGray
        )
    }

}