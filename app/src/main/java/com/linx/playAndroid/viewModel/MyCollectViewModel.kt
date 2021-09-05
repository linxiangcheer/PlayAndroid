package com.linx.playAndroid.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.linx.common.base.BaseViewModel
import com.linx.net.ext.*
import com.linx.net.paging.CommonPagingSource
import com.linx.playAndroid.model.MyCollectData
import com.linx.playAndroid.repo.MyCollectRepo
import kotlinx.coroutines.flow.Flow

/**
 * 我的收藏
 */
class MyCollectViewModel : BaseViewModel() {

    //我的收藏列表数据
    val myCollectListData: Flow<PagingData<MyCollectData>>
        get() = _myCollectListData

    //获取我的收藏列表数据
    private val _myCollectListData = Pager(PagingConfig(pageSize = 20)) {
        CommonPagingSource { nextPage ->
            MyCollectRepo.getMyCollectList(nextPage)
        }
    }.flow.cachedIn(viewModelScope)


}