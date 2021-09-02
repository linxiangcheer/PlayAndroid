package com.linx.playAndroid.widget

import androidx.lifecycle.MutableLiveData
import com.linx.playAndroid.model.ProjectTreeData
import com.linx.playAndroid.model.PublicNumChapterData

/**
 * 存放临时数据
 */
object StoreData {

    //项目页面顶部指示器数据
    val projectTopBarListData = MutableLiveData<List<ProjectTreeData>>()

    //公众号页面顶部指示器数据
    val publicNumTopBarListData = MutableLiveData<List<PublicNumChapterData>>()

}