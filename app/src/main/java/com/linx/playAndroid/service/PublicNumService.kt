package com.linx.playAndroid.service

import com.linx.net.base.NetUrl
import com.linx.net.model.BaseResponse
import retrofit2.Call
import retrofit2.http.GET

interface PublicNumService {

    //获取公众号列表
    @GET(NetUrl.WXARTICLE_CHAPTERS)
    fun getPublicNumChapter(): Call<BaseResponse>

}