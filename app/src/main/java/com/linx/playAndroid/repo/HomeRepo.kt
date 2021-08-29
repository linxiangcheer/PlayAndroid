package com.linx.playAndroid.repo

import com.linx.net.base.ServiceCreator
import com.linx.playAndroid.service.HomeService

object HomeRepo {

    //首页列表
    suspend fun getHomeList(page: Int) = ServiceCreator.create<HomeService>().getHomeList(page)

}