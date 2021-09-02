package com.linx.playAndroid.repo

import com.linx.net.base.ServiceCreator
import com.linx.playAndroid.service.PublicNumService

object PublicNumRepo {

    /**
     * 获取公众号列表
     */
    fun getPublicNumChapter() = ServiceCreator.getService<PublicNumService>().getPublicNumChapter()

    /**
     * 获取某个公众号历史文章列表
     */
    suspend fun getPublicNumList(id: Int, page: Int) = ServiceCreator.getService<PublicNumService>().getPublicNumList(id, page)

}