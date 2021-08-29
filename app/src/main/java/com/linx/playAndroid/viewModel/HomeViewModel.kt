package com.linx.playAndroid.viewModel

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.linx.common.base.BaseViewModel
import com.linx.common.paging.CommonPagingSource
import com.linx.playAndroid.repo.HomeRepo

/**
 * 首页
 */
class HomeViewModel: BaseViewModel() {

    //首页列表
    val homeListData = Pager(PagingConfig(pageSize = 20)) {
        CommonPagingSource { nextPage: Int ->
            HomeRepo.getHomeList(nextPage)
        }
    }.flow.cachedIn(viewModelScope)

}