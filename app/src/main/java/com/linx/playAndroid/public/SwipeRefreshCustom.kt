package com.linx.playAndroid.public

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
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
import com.linx.playAndroid.public.paging.PagingStateUtil

/**
 * 带刷新头的Card布局
 * LazyPagingItems<T>
 */
@Composable
fun <T : Any> SwipeRefreshContent(
    viewModel: ViewModel,
    lazyPagingListData: LazyPagingItems<T>,
    cardHeight: Dp = 120.dp,
    state: LazyListState = rememberLazyListState(),
    itemContent: LazyListScope.() -> Unit = {},
    content: @Composable (index: Int, data: T) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {

        val refreshState = rememberSwipeRefreshState(false)

        SwipeRefresh(
            state = refreshState,
            onRefresh = {
                //显示刷新头
                refreshState.isRefreshing = true
                //刷新数据
                lazyPagingListData.refresh()
                viewModel.sleepTime(3000) {
                    refreshState.isRefreshing = false
                }
            }
        ) {
            //列表数据
            PagingStateUtil().pagingStateUtil(lazyPagingListData, refreshState, viewModel) {
                LazyColumn(modifier = Modifier.fillMaxSize(), state = state) {
                    itemContent()
                    itemsIndexed(lazyPagingListData) { index, data ->
                        SimpleCard(cardHeight = cardHeight) {
                            content(index, data!!)
                        }
                    }
                }
            }
        }

    }
}

/**
 * 带刷新头的Card布局
 * LazyPagingItems<T>
 * Card高度自适应
 */
@Composable
fun <T : Any> SwipeRefreshContent(
    viewModel: ViewModel,
    listData: List<T>?,
    state: LazyListState = rememberLazyListState(),
    noData: () -> Unit,
    content: @Composable (data: T) -> Unit
) {

    if (listData == null) return

    if (listData.isEmpty()) {
        ErrorComposable("暂无数据，请点击重试") {
            noData()
        }
        return
    }

    Column(modifier = Modifier.fillMaxSize()) {

        val refreshState = rememberSwipeRefreshState(false)

        SwipeRefresh(
            state = refreshState,
            onRefresh = {
                //显示刷新头
                refreshState.isRefreshing = true
                //刷新数据
                noData()
                viewModel.sleepTime(3000) {
                    refreshState.isRefreshing = false
                }
            }
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize(), state = state) {
                itemsIndexed(listData) { index, data ->
                    SimpleCard {
                        content(data)
                    }
                }
            }
        }

    }
}