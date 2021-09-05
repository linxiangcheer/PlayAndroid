package com.linx.playAndroid.service

import com.linx.net.base.NetUrl
import com.linx.net.model.BaseResponse
import com.linx.net.paging.CommonalityPageModel
import com.linx.playAndroid.model.MyCollectData
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MyCollectService {

    //我的收藏
    @GET(NetUrl.MY_COLLECT)
    suspend fun getMyCollectList(@Path("page") page: Int): CommonalityPageModel<MyCollectData>

}