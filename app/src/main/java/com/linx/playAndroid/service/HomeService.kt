package com.linx.playAndroid.service

import com.linx.common.paging.CommonalityPageModel
import com.linx.net.base.NetUrl
import com.linx.playAndroid.model.ArticleListData
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 首页页面的网络请求接口
 */
interface HomeService {

    @GET(NetUrl.ARTICLE_LIST)
    suspend fun getHomeList(@Path("page") page: Int): CommonalityPageModel<ArticleListData>

}