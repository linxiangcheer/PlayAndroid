package com.linx.playAndroid.service

import com.linx.net.base.NetUrl
import com.linx.net.model.BaseResponse
import retrofit2.Call
import retrofit2.http.GET

interface MineService {

    //个人积分
    @GET(NetUrl.LG_COIN_USERINFO)
    fun getUserInfoIntegral(): Call<BaseResponse>

}