package com.linx.playAndroid.composable

import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
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
fun HomeCompose(navHostController: NavHostController) {

    val homeViewModel: HomeViewModel = viewModel()

    val homeListData = homeViewModel.homeListData.collectAsLazyPagingItems()

    homeViewModel.getBannerData()

    val bannerListData = homeViewModel.bannerListData.observeAsState()

    //获取置顶数据列表
    homeViewModel.getArticleTopListData()

    val articleTopData = homeViewModel.articleTopList.observeAsState()

    //首页页面的内容
    SwipeRefreshContent(
        homeViewModel,
        homeListData,
        state = homeViewModel.homeLazyListState,
        itemContent = {
            item {
                //轮播图
                Banner(bannerListData.value) { link ->
                    navHostController.navigate("${KeyNavigationRoute.WEBVIEW.route}?url=$link")
                }
            }

            //置顶数据
            articleTopData.value?.let { listData ->
                items(listData) { data ->
                    SimpleCard(cardHeight = 120.dp) {
                        data.apply {
                            HomeCardItemContent(
                                getAuthor(author, shareUser),
                                fresh,
                                true,
                                niceDate ?: "刚刚",
                                title ?: "",
                                superChapterName ?: "未知",
                                collect
                            ) {
                                navHostController.navigate("${KeyNavigationRoute.WEBVIEW.route}?url=$link")
                            }
                        }
                    }
                }
            }
        }) { index, data ->
        data.apply {
            HomeCardItemContent(
                getAuthor(author, shareUser),
                fresh,
                false,
                niceDate ?: "刚刚",
                title ?: "",
                superChapterName ?: "未知",
                collect
            ) {
                navHostController.navigate("${KeyNavigationRoute.WEBVIEW.route}?url=$link")
            }
        }
    }

}