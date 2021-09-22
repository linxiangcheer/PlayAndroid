package com.linx.playAndroid.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.linx.common.base.BaseViewModel
import com.linx.net.ext.*
import com.linx.playAndroid.model.HotKeyData
import com.linx.playAndroid.repo.SearchRepo

/**
 * 搜索
 */
class SearchViewModel : BaseViewModel() {

    private val _hotKeyListData = MutableLiveData<List<HotKeyData>>()
    val hotKeyListData: LiveData<List<HotKeyData>>
        get() = _hotKeyListData

    /**
     * 获取搜索热词
     */
    fun getHotKeyData() = serverAwait {
        SearchRepo.getHotKey().serverData().onSuccess {
            onBizError { code, message ->
                Log.e("xxx", "获取搜索热词 接口异常 $code $message")
            }
            onBizOK<List<HotKeyData>> { code, data, message ->
                _hotKeyListData.postValue(data)
            }
        }.onFailure {
            Log.e("xxx", "获取搜索热词 接口异常 $it")
        }
    }

}