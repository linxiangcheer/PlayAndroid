package com.linx.playAndroid.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.linx.playAndroid.KeyNavigationRoute
import com.linx.playAndroid.public.*
import com.linx.playAndroid.viewModel.HomeViewModel

/**
 * 首页页面
 */
@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
fun HomeCompose(navController: NavController) {

    val homeViewModel: HomeViewModel = viewModel()

    val homeListData = homeViewModel.homeListData.collectAsLazyPagingItems()

    homeViewModel.getBannerData()

    val bannerListData = homeViewModel.bannerListData.observeAsState()

    //首页页面的内容
    SwipeRefreshContent(homeViewModel, homeListData, itemContent = {
        item {
            Banner(bannerListData.value) { link ->
                navController.navigate("${KeyNavigationRoute.WEBVIEW.route}?url=$link")
            }
        }
    }) { index, data ->
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