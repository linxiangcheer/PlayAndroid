package com.linx.playAndroid.viewModel

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.linx.common.base.BaseViewModel
import com.linx.net.paging.CommonPagingSource
import com.linx.playAndroid.model.UserArticleListData
import com.linx.playAndroid.repo.SquareRepo
import kotlinx.coroutines.flow.Flow

/**
 * 广场
 */
class SquareViewModel : BaseViewModel() {

    //广场列表
    val userArticleListData: Flow<PagingData<UserArticleListData>>
        get() = _userArticleListData

    private val _userArticleListData = Pager(PagingConfig(pageSize = 20)) {
        CommonPagingSource { nextPage: Int ->
            SquareRepo.getUserArticleList(nextPage)
        }
    }.flow.cachedIn(viewModelScope)

}