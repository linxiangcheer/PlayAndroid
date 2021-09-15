package com.linx.playAndroid.viewModel

import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.linx.common.base.BaseViewModel
import com.linx.common.baseData.Nav
import com.linx.net.ext.*
import com.linx.net.paging.CommonPagingSource
import com.linx.playAndroid.model.PublicNumChapterData
import com.linx.playAndroid.model.PublicNumListData
import com.linx.playAndroid.repo.PublicNumRepo
import com.linx.playAndroid.widget.StoreData
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow

/**
 * 公众号
 */
class PublicNumViewModel : BaseViewModel() {

    //公众号页面列表状态
    val publicNumLazyListState: LazyListState = LazyListState()

    //保存改变过index和offset的指示器Index
    var saveChangePublicNumIndex = 0

    //公众号id
    private val indexId
        get() = StoreData.publicNumTopBarListData.value?.get(Nav.publicNumIndex.value)?.id ?: 408

    //公众号列表
    private val _publicNumChapter = MutableLiveData<List<PublicNumChapterData>>()
    val publicNumChapter: LiveData<List<PublicNumChapterData>>
        get() = _publicNumChapter

    /**
     * 获取公众号列表
     */
    fun getPublicNumChapterData() = serverAwait {
        PublicNumRepo.getPublicNumChapter().serverData().onSuccess {
            onBizError { code, message ->
                Log.e("xxx", "获取公众号列表 顶部指示器数据 接口异常 $code $message")
            }
            onBizOK<List<PublicNumChapterData>> { code, data, message ->
                _publicNumChapter.postValue(data)
                //临时存储指示器数据
                StoreData.publicNumTopBarListData.postValue(data)
            }
        }.onFailure {
            Log.e("xxx", "获取公众号列表 顶部指示器数据 接口异常 $it")
        }
    }

    //某个公众号历史文章列表数据
    val publicNumListData: Flow<PagingData<PublicNumListData>>
        get() = _publicNumListData

    //获取某个公众号历史文章列表数据
    private val _publicNumListData = Pager(PagingConfig(pageSize = 20)) {
        CommonPagingSource { nextPage: Int ->
            PublicNumRepo.getPublicNumList(indexId, nextPage)
        }
    }.flow.cachedIn(viewModelScope)

}