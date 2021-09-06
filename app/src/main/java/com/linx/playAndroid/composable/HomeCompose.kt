package com.linx.playAndroid.composable

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.linx.playAndroid.KeyNavigationRoute
import com.linx.playAndroid.public.HomeCardItemContent
import com.linx.playAndroid.public.SwipeRefreshContent
import com.linx.playAndroid.public.getAuthor
import com.linx.playAndroid.viewModel.HomeViewModel

/**
 * 首页页面
 */
@Composable
fun HomeCompose(navController: NavController) {

    val homeViewModel: HomeViewModel = viewModel()

    val homeListData = homeViewModel.homeListData.collectAsLazyPagingItems()

    //首页页面的内容
    SwipeRefreshContent(homeViewModel, homeListData) { index, data ->
        data.apply {
            HomeCardItemContent(
                getAuthor(author, shareUser),
                fresh,
                niceDate ?: "刚刚",
                title ?: "",
                superChapterName ?: "未知",
                collect
            ) {
                navController.navigate("${KeyNavigationRoute.WEBVIEW.route}?url=$link")
            }
        }
    }

}