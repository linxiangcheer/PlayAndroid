package com.linx.playAndroid.service

import com.linx.net.base.NetUrl
import com.linx.net.model.BaseResponse
import com.linx.net.paging.CommonalityPageModel
import com.linx.playAndroid.model.PublicNumListData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PublicNumService {

    //获取公众号列表
    @GET(NetUrl.WXARTICLE_CHAPTERS)
    fun getPublicNumChapter(): Call<BaseResponse>

    //获取某个公众号历史文章列表
    @GET(NetUrl.WXARTICLE_LIST)
    suspend fun getPublicNumList(@Path("id") id: Int, @Path("page") page: Int): CommonalityPageModel<PublicNumListData>

}