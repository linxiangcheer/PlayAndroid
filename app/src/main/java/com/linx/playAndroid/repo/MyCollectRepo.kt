package com.linx.playAndroid.repo

import com.linx.net.base.ServiceCreator
import com.linx.playAndroid.service.MyCollectService

object MyCollectRepo {

    /**
     * ζηζΆθ
     */
    suspend fun getMyCollectList(page: Int) = ServiceCreator.getService<MyCollectService>().getMyCollectList(page)

}