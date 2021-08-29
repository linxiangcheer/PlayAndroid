package com.linx.playAndroid.composable

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.linx.common.ext.transitionDate
import com.linx.common.widget.sleepTime
import com.linx.playAndroid.model.ArticleListData
import com.linx.playAndroid.public.HomeCard
import com.linx.playAndroid.public.paging.ErrorPaging
import com.linx.playAndroid.public.paging.pagingStateUtil
import com.linx.playAndroid.ui.theme.c_b66731
import com.linx.playAndroid.viewModel.HomeViewModel

/**
 * 首页页面
 */
@Composable
fun HomeCompose(navController: NavController) {

    val homeViewModel: HomeViewModel = viewModel()

    val refreshState = rememberSwipeRefreshState(false)

    val homeListData = homeViewModel.homeListData.collectAsLazyPagingItems()

    SwipeRefresh(
        state = refreshState,
        onRefresh = {
            //显示刷新头
            refreshState.isRefreshing = true
            homeViewModel.sleepTime(3000) {
                refreshState.isRefreshing = false
            }
        }
    ) {
        //首页列表数据
        pagingStateUtil(homeListData, refreshState, homeViewModel) {
            LazyColumn {
                itemsIndexed(homeListData) { index, data ->
                    HomeCard(120.dp) {
                        HomeCardItemContent(data!!)
                    }
                }
            }
        }

    }

}

/**
 * 首页列表卡片内容
 */
@Composable
private fun HomeCardItemContent(data: ArticleListData) {
    Column(
        //内边距
        modifier = Modifier.fillMaxSize()
            .padding(top = 6.dp, bottom = 6.dp, start = 10.dp, end = 10.dp)
    ) {

        //上面的控件
        topCard(getAuthor(data), data.fresh, data.publishTime)

        //中间的控件
        centerCard(modifier = Modifier.weight(1f, true), data.title ?: "")

        //下面的控件
        bottomCard(data.superChapterName ?: "未知", data.collect)

    }
}

/**
 * 底部部分
 */
@Composable
private fun bottomCard(
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
private fun centerCard(modifier: Modifier = Modifier, title: String) {

    Surface(
        modifier = modifier.padding(top = 6.dp)
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
private fun topCard(
    //作者
    author: String,
    //是否是最新的
    fresh: Boolean,
    //发布时间
    publishTime: Long
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom
    ) {
        grayText(author)
        if (fresh) {
            redText("最新", modifier = Modifier.padding(start = 6.dp))
            redText("置顶", modifier = Modifier.padding(start = 6.dp))
        }

        Column(
            modifier = Modifier.weight(1f, true),
            horizontalAlignment = Alignment.End
        ) {
            grayText(publishTime.transitionDate())
        }
    }
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
private fun getAuthor(data: ArticleListData): String {

    var authorStr: String

    data.apply {
        authorStr = when {
            //isNotBlank 不为空且包含空字符以外的内容
            author?.isNotBlank() == true -> author.toString()
            shareUser?.isNotBlank() == true -> shareUser.toString()
            else -> "佚名"
        }
    }
    return authorStr
}