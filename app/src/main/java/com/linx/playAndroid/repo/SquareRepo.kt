package com.linx.playAndroid.repo

import com.linx.net.base.ServiceCreator
import com.linx.playAndroid.service.SquareService

/**
 * 广场
 */
object SquareRepo {

    //获取广场列表数据
    suspend fun getUserArticleList(page: Int) = ServiceCreator.getService<SquareService>().getUserArticleList(page)

}