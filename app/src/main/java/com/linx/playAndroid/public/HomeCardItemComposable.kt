package com.linx.playAndroid.public

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linx.common.ext.transitionDate
import com.linx.playAndroid.ui.theme.c_b66731

/**
 * 列表卡片内的内容
 */
@Composable
fun HomeCardItemContent(
    //作者
    author: String,
    //新的内容
    fresh: Boolean,
    //是否置顶,
    stick: Boolean = false,
    //发布时间
    niceDate: String,
    //标题
    title: String,
    //来源
    superChapterName: String,
    //是否收藏
    collect: Boolean,
    //是否显示具体时间
    isSpecific: Boolean = true,
    onClick: () -> Unit = {}
) {
    Column(
        //内边距
        modifier = Modifier.clickable(onClick = onClick).fillMaxSize()
            .padding(top = 6.dp, bottom = 6.dp, start = 10.dp, end = 10.dp)
    ) {

        //上面的控件
        TopCard(author, fresh, stick, niceDate, isSpecific)

        //中间的控件
        CenterCard(modifier = Modifier.weight(1f, true), title)

        //下面的控件
        BottomCard(superChapterName, collect)

    }
}

/**
 * 底部部分
 */
@Composable
private fun BottomCard(
    //渠道名
    chapterName: String,
    //是否收藏
    collect: Boolean
) {

    Row(
        modifier = Modifier.padding(top = 2.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        redText(chapterName)
        Spacer(modifier = Modifier.weight(1f, true))
        //收藏
        Icon(
            Icons.Default.Favorite,
            contentDescription = null,
            tint = if (collect) Color.Red else Color.LightGray
        )
    }

}

/**
 * 中间部分
 */
@Composable
private fun CenterCard(modifier: Modifier = Modifier, title: String) {

    Surface(
        modifier = modifier.padding(top = 6.dp),
        color = MaterialTheme.colors.background
    ) {
        Text(
            title,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            color = MaterialTheme.colors.secondaryVariant,
            maxLines = 2
        )
    }

}

/**
 * 上面部分
 */
@Composable
private fun TopCard(
    //作者
    author: String,
    //是否是最新的
    fresh: Boolean,
    //是否置顶
    stick: Boolean,
    //发布时间
    niceDate: String,
    //是否显示具体时间
    isSpecific: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        grayText(author)
        if (fresh) {
            borderText("最新", modifier = Modifier.padding(start = 6.dp))
        }
        if (stick) {
            borderText("置顶", modifier = Modifier.padding(start = 6.dp))
        }

        Column(
            modifier = Modifier.weight(1f, true),
            horizontalAlignment = Alignment.End
        ) {
            grayText(niceDate)
        }
    }
}

/**
 * 带边框的Text
 */
@Composable
private fun borderText(str: String, color: Color = Color.Red, modifier: Modifier = Modifier) {
    Text(
        str,
        modifier = modifier.border(BorderStroke(1.dp, color), RoundedCornerShape(4.dp))
            .padding(start = 3.dp, end = 3.dp),
        color = color,
        fontSize = 10.sp
    )
}

/**
 * 红色文字
 */
@Composable
private fun redText(str: String = "", modifier: Modifier = Modifier) {
    Text(
        str,
        modifier = modifier,
        color = c_b66731,
        fontSize = 14.sp
    )
}

/**
 * 灰色文字
 */
@Composable
private fun grayText(str: String = "", modifier: Modifier = Modifier) {
    Text(
        str,
        modifier = modifier,
        color = Color.LightGray,
        fontSize = 12.sp
    )
}

/**
 * 获取发布者昵称
 */
fun getAuthor(author: String?, shareUser: String?): String {
    return when {
        //isNotBlank 不为空且包含空字符以外的内容
        author?.isNotBlank() == true -> author.toString()
        shareUser?.isNotBlank() == true -> shareUser.toString()
        else -> "佚名"
    }
}