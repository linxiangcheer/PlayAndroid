package com.linx.playAndroid.repo

import com.linx.net.base.ServiceCreator
import com.linx.playAndroid.service.HomeService

object HomeRepo {

    //首页列表
    suspend fun getHomeList(page: Int) = ServiceCreator.getService<HomeService>().getHomeList(page)

    //首页轮播图
    fun getBanner() = ServiceCreator.getService<HomeService>().getBanner()

    //获取置顶文章列表
    fun getArticleTopList() = ServiceCreator.getService<HomeService>().getArticleTopList()

}