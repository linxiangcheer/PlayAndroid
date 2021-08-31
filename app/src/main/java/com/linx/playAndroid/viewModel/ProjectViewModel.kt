package com.linx.playAndroid.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.linx.common.base.BaseViewModel
import com.linx.net.ext.*
import com.linx.playAndroid.model.ProjectTreeData
import com.linx.playAndroid.repo.ProjectRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * 项目
 */
class ProjectViewModel : BaseViewModel() {

    //指示器index
    val projectTreeIndex = mutableStateOf(0)

    //项目页面顶部指示器
    private val _projectTreeData = MutableLiveData<List<ProjectTreeData>>()
    val projectTreeData: LiveData<List<ProjectTreeData>>
        get() = _projectTreeData

    /**
     * 获取项目页面顶部指示器数据
     */
    fun getProjectTreeData() = serverAwait {
        ProjectRepo.getProjectTree().serverData().onSuccess {
            onBizError { code, message ->
                Log.e("xxx", "获取项目页面顶部指示器数据 接口异常")
            }
            onBizOK<List<ProjectTreeData>> { code, data, message ->
                Log.i("xxx", "数据为 $data")
                _projectTreeData.postValue(data)
            }
        }.onFailure {
            Log.e("xxx", "获取项目页面顶部指示器数据 接口异常")
        }
    }

}