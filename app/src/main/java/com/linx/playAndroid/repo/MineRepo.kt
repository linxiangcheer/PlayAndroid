package com.linx.playAndroid.repo

import com.linx.net.base.ServiceCreator
import com.linx.playAndroid.service.MineService

object MineRepo {

    /**
     * 获取个人积分
     */
    fun getUserInfoIntegral() = ServiceCreator.getService<MineService>().getUserInfoIntegral()

}