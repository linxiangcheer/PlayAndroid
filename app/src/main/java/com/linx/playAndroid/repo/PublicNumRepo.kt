package com.linx.playAndroid.repo

import com.linx.net.base.ServiceCreator
import com.linx.playAndroid.service.PublicNumService

object PublicNumRepo {

    /**
     * 获取公众号列表
     */
    fun getPublicNumChapter() = ServiceCreator.getService<PublicNumService>().getPublicNumChapter()

}