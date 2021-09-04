package com.linx.playAndroid.repo

import com.linx.net.base.ServiceCreator
import com.linx.playAndroid.service.IntegralService

object IntegralRepo {

    /**
     * 获取积分排行
     */
    suspend fun getCoinRankList(page: Int) = ServiceCreator.getService<IntegralService>().getCoinRankList(page)

}