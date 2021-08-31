package com.linx.playAndroid.service

import com.linx.net.base.NetUrl
import com.linx.net.model.BaseResponse
import com.linx.playAndroid.model.ProjectTreeData
import retrofit2.Call
import retrofit2.http.GET

interface ProjectService {

    //项目分类
    @GET(NetUrl.PROJECT_TREE)
    fun getProjectTree(): Call<BaseResponse>

}