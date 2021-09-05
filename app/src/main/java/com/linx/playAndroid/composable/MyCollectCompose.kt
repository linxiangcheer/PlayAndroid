package com.linx.playAndroid.composable

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.linx.playAndroid.model.MyCollectData
import com.linx.playAndroid.public.*
import com.linx.playAndroid.viewModel.MyCollectViewModel

/**
 * 我的收藏页面
 */
@Composable
fun MyCollectCompose(navController: NavController) {
    
    val myCollectViewModel: MyCollectViewModel = viewModel()

    //获取我的收藏列表数据
    val myCollectListData = myCollectViewModel.myCollectListData.collectAsLazyPagingItems()

    BaseScreen {
        Scaffold(
            topBar = {
                AppBar("我的收藏", leftIcon = Icons.Default.ArrowBack, onLeftClick = {
                    navController.navigateUp()
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
                            publishTime,
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

/**
 * 我的收藏页面布局
 */
@Composable
private fun MyCollectScreen() {

}