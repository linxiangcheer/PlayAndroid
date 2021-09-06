package com.linx.playAndroid.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.linx.common.base.BaseViewModel
import com.linx.common.baseData.CommonConstant
import com.linx.common.widget.SpUtilsMMKV
import com.linx.net.ext.*
import com.linx.playAndroid.model.UserInfoIntegralData
import com.linx.playAndroid.repo.MineRepo

/**
 * 我的
 */
class MineViewModel : BaseViewModel() {

    //个人积分
    private val _userInfoIntegral = MutableLiveData<UserInfoIntegralData>()
    val userInfoIntegral = _userInfoIntegral

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

}