package com.linx.playAndroid.service

import com.linx.net.base.NetUrl
import com.linx.net.model.BaseResponse
import com.linx.net.paging.CommonalityPageModel
import com.linx.playAndroid.model.MyCollectData
import com.linx.playAndroid.model.MyShareArticlesListData
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MyShareArticlesService {

    //我的文章
    @GET(NetUrl.MY_SHARE_ARTICLES)
    suspend fun getMyShareArticles(@Path("page") page: Int): MyShareArticlesListData<MyCollectData>

}