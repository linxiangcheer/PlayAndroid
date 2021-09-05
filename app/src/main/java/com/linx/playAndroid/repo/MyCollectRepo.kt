package com.linx.playAndroid.repo

import com.linx.net.base.ServiceCreator
import com.linx.playAndroid.service.MyCollectService

object MyCollectRepo {

    /**
     * 我的收藏
     */
    suspend fun getMyCollectList(page: Int) = ServiceCreator.getService<MyCollectService>().getMyCollectList(page)

}