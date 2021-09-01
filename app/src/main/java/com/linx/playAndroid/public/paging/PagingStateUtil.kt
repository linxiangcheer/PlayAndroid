package com.linx.playAndroid.public.paging

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.linx.common.widget.sleepTime
import com.linx.playAndroid.public.ErrorComposable

/**
 * 统一管理Paging数据状态的方法
 * 错误处理、加载中的显示方式
 */
@Composable
fun <T : Any> pagingStateUtil(
    //paging数据
    pagingData: LazyPagingItems<T>,
    //刷新状态
    refreshState: SwipeRefreshState,
    viewModel: ViewModel,
    content: @Composable () -> Unit
) {

    when (pagingData.loadState.refresh) {
        //未加载且未观察到错误
        is LoadState.NotLoading -> NotLoading(refreshState, viewModel, content)
        //加载失败
        is LoadState.Error -> Error(pagingData, refreshState)
        //加载中
        LoadState.Loading -> Loading(refreshState)
    }

    //如果在加载途中遇到错误的话，pagingData的状态为append
    when (pagingData.loadState.append) {
        //加载失败
        is LoadState.Error -> Error(pagingData, refreshState)
        //加载中
        LoadState.Loading -> Loading(refreshState)
    }

}

/**
 * 未加载且未观察到错误
 */
@Composable
private fun NotLoading(
    refreshState: SwipeRefreshState,
    viewModel: ViewModel,
    content: @Composable () -> Unit
) {
    content()

    //让刷新头停留一下子再收回去
    viewModel.sleepTime {
        refreshState.isRefreshing = false
    }
}

/**
 * 加载失败
 */
@Composable
private fun <T : Any> Error(
    pagingData: LazyPagingItems<T>,
    refreshState: SwipeRefreshState
) {
    refreshState.isRefreshing = false
    ErrorComposable {
        pagingData.refresh()
    }
}

/**
 * 加载中
 */
@Composable
private fun Loading(refreshState: SwipeRefreshState) {
    Row(modifier = Modifier.fillMaxSize()) { }
    //显示刷新头
    if (!refreshState.isRefreshing) refreshState.isRefreshing = true
}