package com.linx.playAndroid.service

import com.linx.net.base.NetUrl
import com.linx.net.paging.CommonalityPageModel
import com.linx.playAndroid.model.CoinRankData
import retrofit2.http.GET
import retrofit2.http.Path

interface IntegralService {

    //积分排行
    @GET(NetUrl.COIN_RANK)
    suspend fun getCoinRankList(@Path("page") page: Int): CommonalityPageModel<CoinRankData>

}