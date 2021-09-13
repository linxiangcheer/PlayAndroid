package com.linx.playAndroid.composable

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.linx.common.baseData.Nav
import com.linx.common.ext.transitionDate
import com.linx.playAndroid.KeyNavigationRoute
import com.linx.playAndroid.model.PublicNumListData
import com.linx.playAndroid.public.*
import com.linx.playAndroid.viewModel.PublicNumViewModel

/**
 * 公众号页面
 */
@Composable
fun PublicNumCompose(navController: NavController) {

    val publicNumViewModel: PublicNumViewModel = viewModel()

    //某个公众号历史文章列表数据
    val publicNumListData = publicNumViewModel.publicNumListData.collectAsLazyPagingItems()

    //如果TopBar的Index改变的话需要刷新数据
    LaunchedEffect(Nav.publicNumIndex.value) {
        publicNumListData.refresh()
    }

    //公众号页面的内容
    SwipeRefreshContent(publicNumViewModel, publicNumListData) { index, data ->
        data.apply {
            HomeCardItemContent(
                getAuthor(author, shareUser),
                fresh,
                false,
                niceDate ?: "刚刚",
                title ?: "",
                if (superChapterName != null) "$superChapterName" else "未知",
                collect,
                isSpecific = false
            ) {
                navController.navigate("${KeyNavigationRoute.WEBVIEW.route}?url=$link")
            }
        }
    }

}