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
import com.linx.net.ext.*
import com.linx.net.paging.CommonPagingSource
import com.linx.playAndroid.model.NaviData
import com.linx.playAndroid.model.SystemData
import com.linx.playAndroid.model.UserArticleListData
import com.linx.playAndroid.repo.SquareRepo
import kotlinx.coroutines.flow.Flow

/**
 * 广场
 */
class SquareViewModel : BaseViewModel() {

    //广场
    val squareIndexState: LazyListState = LazyListState()

    //每日一问
    val questionIndexState: LazyListState = LazyListState()

    //体系
    val systemIndexState: LazyListState = LazyListState()

    //导航
    val naviIndexState: LazyListState = LazyListState()

    //广场列表
    val userArticleListData: Flow<PagingData<UserArticleListData>>
        get() = _userArticleListData

    private val _userArticleListData = Pager(PagingConfig(pageSize = 20)) {
        CommonPagingSource { nextPage: Int ->
            SquareRepo.getUserArticleList(nextPage)
        }
    }.flow.cachedIn(viewModelScope)

    //问答列表
    val questionAnswerData: Flow<PagingData<UserArticleListData>>
        get() = _questionAnswerData

    private val _questionAnswerData = Pager(PagingConfig(pageSize = 20)) {
        CommonPagingSource { nextPage: Int ->
            SquareRepo.getQuestionAnswer(nextPage)
        }
    }.flow.cachedIn(viewModelScope)

    //体系数据
    private val _systemData = MutableLiveData<List<SystemData>>()
    val systemData: LiveData<List<SystemData>>
        get() = _systemData

    //获取体系数据
    fun getSystemData() = serverAwait {
        SquareRepo.getSystem().serverData().onSuccess {
            onBizError { code, message ->
                Log.e("xxx", "获取体系数据 接口异常 $code $message")
            }
            onBizOK<List<SystemData>> { code, data, message ->
                _systemData.postValue(data)
            }
        }.onFailure {
            Log.e("xxx", "获取体系数据 接口异常$it")
        }
    }

    //导航数据
    private val _naviData = MutableLiveData<List<NaviData>>()
    val naviData: LiveData<List<NaviData>>
        get() = _naviData

    //获取导航数据
    fun getNavi() = serverAwait {
        SquareRepo.getNavi().serverData().onSuccess {
            onBizError { code, message ->
                Log.e("xxx", "获取导航数据 接口异常 $code $message")
            }
            onBizOK<List<NaviData>> { code, data, message ->
                _naviData.postValue(data)
            }
        }.onFailure {
            Log.e("xxx", "获取导航数据 接口异常$it")
        }
    }

}