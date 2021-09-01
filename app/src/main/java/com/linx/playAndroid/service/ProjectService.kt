package com.linx.playAndroid.service

import com.linx.net.paging.CommonalityPageModel
import com.linx.net.base.NetUrl
import com.linx.net.model.BaseResponse
import com.linx.playAndroid.model.ProjectListData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProjectService {

    //项目分类
    @GET(NetUrl.PROJECT_TREE)
    fun getProjectTree(): Call<BaseResponse>

    //项目列表数据
    @GET(NetUrl.PROJECT_LIST)
    suspend fun getProjectList(@Path("page") page: Int, @Query("cid") cid: Int): CommonalityPageModel<ProjectListData>

}