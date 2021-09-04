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
import com.linx.playAndroid.model.CoinRankData
import com.linx.playAndroid.model.UserInfoIntegralData
import com.linx.playAndroid.repo.IntegralRepo
import com.linx.playAndroid.repo.MineRepo
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow

/**
 * 积分排名
 */
class IntegralRankViewModel : BaseViewModel() {

    //是否请求了个人积分数据
    var isRequestUserInfoData = false

    //个人积分
    private val _userInfoIntegral = MutableLiveData<UserInfoIntegralData>()
    val userInfoIntegral: LiveData<UserInfoIntegralData>
        get() = _userInfoIntegral

    /**
     * 获取个人积分数据
     */
    fun getUserInfoIntegral() = serverAwait {

        MineRepo.getUserInfoIntegral().serverData().onSuccess {
            onBizError { code, message ->
                Log.e("xxx", "获取个人积分数据 接口异常 $code $message")
            }
            onBizOK<UserInfoIntegralData> { code, data, message ->
                _userInfoIntegral.postValue(data)
            }
        }.onFailure {
            Log.e("xxx", "获取个人积分数据 接口异常 $it")
        }

    }

    //积分排行
    val coinRankListData: Flow<PagingData<CoinRankData>>
        get() = _coinRankListData

    //获取积分排行
    private val _coinRankListData = Pager(PagingConfig(pageSize = 20)) {
        CommonPagingSource { nextPage: Int ->
            IntegralRepo.getCoinRankList(nextPage)
        }
    }.flow.cachedIn(viewModelScope)

}