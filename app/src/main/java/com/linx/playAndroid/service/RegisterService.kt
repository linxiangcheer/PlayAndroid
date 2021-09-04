package com.linx.playAndroid.service

import com.linx.net.base.NetUrl
import com.linx.net.model.BaseResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RegisterService {

    //注册
    @FormUrlEncoded
    @POST(NetUrl.USER_REGISTER)
    fun getUserRegister(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String,
    ): Call<BaseResponse>

}