package com.linx.net.base

object NetUrl {

    const val BASE_URL = "https://www.wanandroid.com"

    //首页列表
    const val ARTICLE_LIST = "/article/list/{page}/json"

    //项目分类(指示器)
    const val PROJECT_TREE = "/project/tree/json"

    //项目列表数据
    const val PROJECT_LIST = "/project/list/{page}/json"

    //广场
    const val USER_ARTICLE_LIST = "/user_article/list/{page}/json"

    //问答
    const val WEN_DA = "/wenda/list/{page}/json"

    //体系
    const val SYSTEM = "/tree/json"

    //导航数据
    const val NAVI = "/navi/json"

    //公众号列表
    const val WXARTICLE_CHAPTERS = "/wxarticle/chapters/json"

    //某个公众号历史数据列表
    const val WXARTICLE_LIST = "/wxarticle/list/{id}/{page}/json"

}