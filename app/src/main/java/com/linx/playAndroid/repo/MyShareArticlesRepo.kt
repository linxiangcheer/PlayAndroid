package com.linx.playAndroid.repo

import com.linx.net.base.ServiceCreator
import com.linx.playAndroid.service.MyCollectService
import com.linx.playAndroid.service.MyShareArticlesService

object MyShareArticlesRepo {

    /**
     * 我的文章
     */
    suspend fun getMyShareArticles(page: Int) = ServiceCreator.getService<MyShareArticlesService>().getMyShareArticles(page)

}