package com.linx.playAndroid.repo

import com.linx.net.base.ServiceCreator
import com.linx.playAndroid.service.SquareService

/**
 * 广场
 */
object SquareRepo {

    //获取广场列表数据
    suspend fun getUserArticleList(page: Int) = ServiceCreator.getService<SquareService>().getUserArticleList(page)

    //获取问答列表数据
    suspend fun getQuestionAnswer(page: Int) = ServiceCreator.getService<SquareService>().getQuestionAnswers(page)

    //获取体系数据
    fun getSystem() = ServiceCreator.getService<SquareService>().getSystem()

    //获取导航数据
    fun getNavi() = ServiceCreator.getService<SquareService>().getNavi()

}