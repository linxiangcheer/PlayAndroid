package com.linx.common.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linx.common.baseData.CommonConstant
import com.linx.common.ext.addTo
import com.linx.common.model.ThemeType
import com.linx.common.widget.SpUtilsMMKV
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * ViewModel的基类
 */
abstract class BaseViewModel: ViewModel() {

    //job列表
    private val jobs: MutableList<Job> = mutableListOf<Job>()

    //标记网络loading状态
    val isLoading = MutableLiveData<Boolean>()

    protected fun serverAwait(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch {
        //协程启动之前
        isLoading.value = true
        block.invoke(this)
        //协程启动之后
        isLoading.value = false
    }.addTo(jobs)

    //取消全部协程
    override fun onCleared() {
        jobs.forEach { it.cancel() }
        super.onCleared()
    }

    //是否登录
    fun isLogin(): Boolean = SpUtilsMMKV.getBoolean(CommonConstant.IS_LOGIN) == true

}