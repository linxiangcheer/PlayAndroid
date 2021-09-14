package com.linx.playAndroid.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.linx.playAndroid.model.MyCollectData
import com.linx.playAndroid.public.*
import com.linx.playAndroid.viewModel.MyCollectViewModel

/**
 * 我的收藏页面
 */
@Composable
fun MyCollectCompose(navHostController: NavHostController) {
    
    val myCollectViewModel: MyCollectViewModel = viewModel()

    //布局
    MyCollectScreen(navHostController, myCollectViewModel)

}

/**
 * 我的收藏页面布局
 */
@Composable
private fun MyCollectScreen(navHostController: NavHostController, myCollectViewModel: MyCollectViewModel) {

    //获取我的收藏列表数据
    val myCollectListData = myCollectViewModel.myCollectListData.collectAsLazyPagingItems()

    BaseScreen {
        Scaffold(
            topBar = {
                AppBar("我的收藏", leftIcon = Icons.Default.ArrowBack, onLeftClick = {
                    navHostController.navigateUp()
                })
            },
            content = { paddingValues: PaddingValues ->
                SwipeRefreshContent(
                    myCollectViewModel,
                    myCollectListData
                ) { index: Int, data: MyCollectData ->
                    data.apply {
                        HomeCardItemContent(
                            getAuthor(author, null),
                            false,
                            false,
                            niceDate ?: "刚刚",
                            title ?: "",
                            chapterName ?: "未知",
                            true
                        )
                    }
                }
            }
        )
    }

}