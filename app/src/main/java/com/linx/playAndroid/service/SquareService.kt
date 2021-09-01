package com.linx.playAndroid.service

import com.linx.net.base.NetUrl
import com.linx.net.model.BaseResponse
import com.linx.net.paging.CommonalityPageModel
import com.linx.playAndroid.model.UserArticleListData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 广场页面网络请求接口
 * questions and answers
 */
interface SquareService {

    //广场数据
    @GET(NetUrl.USER_ARTICLE_LIST)
    suspend fun getUserArticleList(@Path("page") page: Int): CommonalityPageModel<UserArticleListData>

    //问答数据
    @GET(NetUrl.WEN_DA)
    suspend fun getQuestionAnswers(@Path("page") page: Int): CommonalityPageModel<UserArticleListData>

    //体系数据
    @GET(NetUrl.SYSTEM)
    fun getSystem(): Call<BaseResponse>

    //导航数据
    @GET(NetUrl.NAVI)
    fun getNavi(): Call<BaseResponse>

}