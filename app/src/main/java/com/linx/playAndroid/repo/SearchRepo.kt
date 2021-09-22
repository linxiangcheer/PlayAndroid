package com.linx.playAndroid.repo

import com.linx.net.base.ServiceCreator
import com.linx.playAndroid.service.SearchService

object SearchRepo {

    //获取搜索热词
    fun getHotKey() = ServiceCreator.getService<SearchService>().getHotKey()

}