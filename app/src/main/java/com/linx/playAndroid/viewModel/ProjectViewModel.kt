package com.linx.playAndroid.viewModel

import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.mutableStateOf
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
import com.linx.playAndroid.model.ProjectListData
import com.linx.playAndroid.model.ProjectTreeData
import com.linx.playAndroid.repo.ProjectRepo
import com.linx.playAndroid.widget.StoreData
import kotlinx.coroutines.flow.Flow

/**
 * 项目
 */
class ProjectViewModel : BaseViewModel() {

    //项目页面列表状态
    val projectLazyListState: LazyListState = LazyListState()

    //保存改变过index和offset的指示器Index
    var saveChangeProjectIndex = 0

    //选中分类的cid
    private val indexCid
        get() = StoreData.projectTopBarListData.value?.get(Nav.projectTopBarIndex.value)?.id ?: 0

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
                Log.e("xxx", "获取项目页面顶部指示器数据 接口异常 $code $message")
            }
            onBizOK<List<ProjectTreeData>> { code, data, message ->
                _projectTreeData.postValue(data)
                //临时存储指示器数据
                StoreData.projectTopBarListData.postValue(data)

                //todo 获取了之后查看有多少个Index，就创建多少个LazyListState
            }
        }.onFailure {
            Log.e("xxx", "获取项目页面顶部指示器数据 接口异常 $it")
        }
    }

    //项目列表数据
    val projectListData: Flow<PagingData<ProjectListData>>
        get() = _projectListData

    private val _projectListData = Pager(PagingConfig(pageSize = 20)) {
        CommonPagingSource { nextPage: Int ->
            ProjectRepo.getProjectList(nextPage, indexCid)
        }
    }.flow.cachedIn(viewModelScope)

}