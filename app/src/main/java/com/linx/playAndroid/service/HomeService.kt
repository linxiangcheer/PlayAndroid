package com.linx.playAndroid.service

import com.linx.net.paging.CommonalityPageModel
import com.linx.net.base.NetUrl
import com.linx.net.model.BaseResponse
import com.linx.playAndroid.model.ArticleListData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 首页页面的网络请求接口
 */
interface HomeService {

    @GET(NetUrl.ARTICLE_LIST)
    suspend fun getHomeList(@Path("page") page: Int): CommonalityPageModel<ArticleListData>

    //首页轮播图
    @GET(NetUrl.HOME_BANNER)
    fun getBanner(): Call<BaseResponse>

    //置顶文章
    @GET(NetUrl.ARTICLE_TOP)
    fun getArticleTopList(): Call<BaseResponse>

}