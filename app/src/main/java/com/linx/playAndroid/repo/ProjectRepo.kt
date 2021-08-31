package com.linx.playAndroid.repo

import android.util.Log
import com.linx.net.base.ServiceCreator
import com.linx.playAndroid.service.ProjectService

object ProjectRepo {

    /**
     * 获取项目分类
     */
    fun getProjectTree() = ServiceCreator.getService<ProjectService>().getProjectTree()


}