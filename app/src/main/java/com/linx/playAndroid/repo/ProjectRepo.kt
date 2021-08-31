package com.linx.playAndroid.repo

import android.util.Log
import com.linx.net.base.ServiceCreator
import com.linx.playAndroid.service.ProjectService

object ProjectRepo {

    /**
     * 获取项目分类
     */
    fun getProjectTree() = ServiceCreator.getService<ProjectService>().getProjectTree()

    /**
     * 获取项目列表数据
     * [page] 页码
     * [cid] 分类的id
     */
    suspend fun getProjectList(page: Int, cid: Int) = ServiceCreator.getService<ProjectService>().getProjectList(page, cid)

}