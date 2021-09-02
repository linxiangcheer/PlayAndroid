package com.linx.playAndroid.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.linx.common.base.BaseViewModel
import com.linx.common.baseData.Nav
import com.linx.net.ext.*
import com.linx.playAndroid.model.PublicNumChapterData
import com.linx.playAndroid.repo.PublicNumRepo
import kotlinx.coroutines.async

/**
 * 公众号
 */
class PublicNumViewModel : BaseViewModel() {

    //公众号id
    private val indexId
        get() = _publicNumChapter.value?.get(Nav.publicNumIndex.value)?.id ?: 0

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
            }
        }.onFailure {
            Log.e("xxx", "获取公众号列表 顶部指示器数据 接口异常 $it")
        }
    }

}