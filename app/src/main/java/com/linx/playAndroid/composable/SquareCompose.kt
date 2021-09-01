package com.linx.playAndroid.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.linx.common.baseData.Nav
import com.linx.playAndroid.public.HomeCardItemContent
import com.linx.playAndroid.public.SwipeRefreshContent
import com.linx.playAndroid.public.SystemCardItemContent
import com.linx.playAndroid.public.getAuthor
import com.linx.playAndroid.viewModel.SquareViewModel

/**
 * 广场页面
 */
@Composable
fun SquareCompose(navController: NavController) {

    val squareViewModel: SquareViewModel = viewModel()

    //广场和问答页面
    if (Nav.squareTopBarIndex.value == 0 || Nav.squareTopBarIndex.value == 1) {
        SquareAndQuestionComposable(Nav.squareTopBarIndex.value, squareViewModel)
    }

    val systemData = squareViewModel.systemData.observeAsState()

    //体系页面
    if (Nav.squareTopBarIndex.value == 2) {
        if (systemData.value == null) {
            //请求体系数据
            squareViewModel.getSystemData()
        }

        SwipeRefreshContent(
            squareViewModel,
            systemData.value,
            noData = { squareViewModel.getSystemData() },
        ) { data ->
            SystemCardItemContent(data.name ?: "", data.children)
        }
    }

}

/**
 * 广场和问答页面
 */
@Composable
private fun SquareAndQuestionComposable(index: Int, squareViewModel: SquareViewModel) {

    //广场数据
    val userArticleListData = squareViewModel.userArticleListData.collectAsLazyPagingItems()

    //问答数据
    val questionAnswerData = squareViewModel.questionAnswerData.collectAsLazyPagingItems()

    //广场页面广场模块内容
    SwipeRefreshContent(
        squareViewModel,
        when (index) {
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