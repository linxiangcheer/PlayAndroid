package com.linx.playAndroid.service

import com.linx.net.base.NetUrl
import com.linx.net.model.BaseResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {

    //登录
    @FormUrlEncoded
    @POST(NetUrl.USER_LOGIN)
    fun getUserLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<BaseResponse>

}