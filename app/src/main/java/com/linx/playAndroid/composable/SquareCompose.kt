package com.linx.playAndroid.composable

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.linx.common.base.BaseViewModel
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

    val userArticleListData = squareViewModel.userArticleListData.collectAsLazyPagingItems()

    //广场页面广场模块内容
    SwipeRefreshContent(squareViewModel, userArticleListData) { data ->
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