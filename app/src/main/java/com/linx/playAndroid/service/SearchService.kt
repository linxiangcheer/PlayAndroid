package com.linx.playAndroid.service

import com.linx.net.base.NetUrl
import com.linx.net.model.BaseResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * 搜索页面
 */
interface SearchService {

    //搜索热词
    @GET(NetUrl.HOTKEY)
    fun getHotKey(): Call<BaseResponse>

}