package com.linx.playAndroid.composable

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.linx.common.baseData.Nav
import com.linx.playAndroid.public.HomeCardItemContent
import com.linx.playAndroid.public.SwipeRefreshContent
import com.linx.playAndroid.public.getAuthor
import com.linx.playAndroid.viewModel.SquareViewModel

/**
 * 广场页面
 */
@Composable
fun SquareCompose(navController: NavController) {

    val squareViewModel: SquareViewModel = viewModel()

    //广场数据
    val userArticleListData = squareViewModel.userArticleListData.collectAsLazyPagingItems()

    //问答数据
    val questionAnswerData = squareViewModel.questionAnswerData.collectAsLazyPagingItems()

    val index = Nav.squareTopBarIndex.value

    //广场页面广场模块内容
    SwipeRefreshContent(squareViewModel,
        when(index) {
            0 -> userArticleListData
            else -> questionAnswerData
        }
    ) { data ->
        data.apply {
            HomeCardItemContent(
                getAuthor(author, shareUser),
                fresh,
                publishTime,
                title ?: "",
                superChapterName ?: "未知",
                collect
            )
        }
    }

}