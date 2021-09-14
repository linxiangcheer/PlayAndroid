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
import com.linx.playAndroid.viewModel.MyShareArticlesViewModel

/**
 * 我的文章页面
 */
@Composable
fun MyShareArticlesCompose(navHostController: NavHostController) {

    val myShareArticlesViewModel: MyShareArticlesViewModel = viewModel()

    //布局
    MyShareArticlesScreen(navHostController, myShareArticlesViewModel)
}

/**
 * 我的文章布局
 */
@Composable
private fun MyShareArticlesScreen(
    navHostController: NavHostController,
    myShareArticlesViewModel: MyShareArticlesViewModel
) {

    val myShareArticlesListData = myShareArticlesViewModel.myShareArticlesListData.collectAsLazyPagingItems()

    BaseScreen {
        Scaffold(
            topBar = {
                AppBar("我分享的文章", leftIcon = Icons.Default.ArrowBack, onLeftClick = {
                    navHostController.navigateUp()
                })
            },
            content = { paddingValues: PaddingValues ->
                SwipeRefreshContent(
                    myShareArticlesViewModel,
                    myShareArticlesListData
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