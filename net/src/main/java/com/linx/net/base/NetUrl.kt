package com.linx.net.base

object NetUrl {

    const val BASE_URL = "https://www.wanandroid.com"

    //首页轮播图
    const val HOME_BANNER = "/banner/json"

    //首页列表
    const val ARTICLE_LIST = "/article/list/{page}/json"

    //置顶文章
    const val ARTICLE_TOP = "/article/top/json"

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

    //个人信息
    const val LG_COIN_USERINFO = "/lg/coin/userinfo/json"

    //登录
    const val USER_LOGIN = "/user/login"

    //注册
    const val USER_REGISTER = "/user/register"

    //积分排行
    const val COIN_RANK = "/coin/rank/{page}/json"

    //我的收藏
    const val MY_COLLECT = "/lg/collect/list/{page}/json"

    //我分享的文章
    const val MY_SHARE_ARTICLES = "/user/lg/private_articles/{page}/json"

    //搜索热词
    const val HOTKEY = "/hotkey/json"

}