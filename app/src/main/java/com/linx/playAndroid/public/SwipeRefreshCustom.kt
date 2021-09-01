package com.linx.playAndroid.public

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.linx.common.widget.sleepTime
import com.linx.playAndroid.public.paging.pagingStateUtil

/**
 * 带刷新头的Card布局
 * 铺满
 */
@Composable
fun <T: Any> SwipeRefreshContent(
    viewModel: ViewModel,
    lazyPagingListData: LazyPagingItems<T>,
    cardHeight: Dp = 120.dp,
    content: @Composable (data: T) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {

        val refreshState = rememberSwipeRefreshState(false)

        SwipeRefresh(
            state = refreshState,
            onRefresh = {
                //显示刷新头
                refreshState.isRefreshing = true
                viewModel.sleepTime(3000) {
                    refreshState.isRefreshing = false
                }
            }
        ) {
            //首页列表数据
            pagingStateUtil(lazyPagingListData, refreshState, viewModel) {
                LazyColumn {
                    itemsIndexed(lazyPagingListData) { index, data ->
                        SimpleCard(cardHeight) {
                            content(data!!)
                        }
                    }
                }
            }
        }

    }
}