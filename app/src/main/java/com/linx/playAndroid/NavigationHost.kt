package com.linx.playAndroid

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.navigation
import com.linx.common.baseData.Nav
import com.linx.common.widget.TwoBackFinish
import com.linx.playAndroid.composable.*
import com.linx.playAndroid.widget.StatsBarUtil

/**
 * 内容 导航
 */
@Composable
fun NavigationHost(navController: NavHostController, onFinish: () -> Unit) {

    val context = LocalContext.current

    NavHost(
        navController,
        startDestination = KeyNavigationRoute.MAIN.route,
        builder = {

            //主页面
            navigation(
                route = KeyNavigationRoute.MAIN.route,
                startDestination = Nav.BottomNavScreen.HomeScreen.route
            ) {
                //首页
                composable(Nav.BottomNavScreen.HomeScreen.route) {

                    //系统颜色的状态栏
                    StatsBarUtil().StatsBarColor(false)

                    HomeCompose(navController)

                    //点击两次返回才关闭app
                    BackHandler {
                        TwoBackFinish().execute(context, onFinish)
                    }
                }
                //项目页面
                composable(Nav.BottomNavScreen.ProjectScreen.route) {

                    //系统颜色的状态栏
                    StatsBarUtil().StatsBarColor(false)

                    ProjectCompose(navController)

                    //点击两次返回才关闭app
                    BackHandler {
                        TwoBackFinish().execute(context, onFinish)
                    }
                }
                //广场页面
                composable(Nav.BottomNavScreen.SquareScreen.route) {

                    //系统颜色的状态栏
                    StatsBarUtil().StatsBarColor(false)

                    SquareCompose(navController)

                    //点击两次返回才关闭app
                    BackHandler {
                        TwoBackFinish().execute(context, onFinish)
                    }
                }
                //公众号页面
                composable(Nav.BottomNavScreen.PublicNumScreen.route) {

                    //系统颜色的状态栏
                    StatsBarUtil().StatsBarColor(false)

                    PublicNumCompose(navController)

                    //点击两次返回才关闭app
                    BackHandler {
                        TwoBackFinish().execute(context, onFinish)
                    }
                }
                //我的页面
                composable(Nav.BottomNavScreen.MineScreen.route) {

                    //系统颜色的状态栏
                    StatsBarUtil().StatsBarColor(false)

                    MineCompose(navController)

                    //点击两次返回才关闭app
                    BackHandler {
                        TwoBackFinish().execute(context, onFinish)
                    }
                }
            }

            //登录页面
            composable(route = KeyNavigationRoute.LOGIN.route) {
                //透明/沉浸式状态栏
                StatsBarUtil().StatsBarColor(true)

                LoginCompose(navController)

                BackHandler { navController.navigateUp() }
            }

            //注册页面
            composable(route = KeyNavigationRoute.REGISTER.route) {
                //透明/沉浸式状态栏
                StatsBarUtil().StatsBarColor(true)

                RegisterCompose(navController)

                BackHandler { navController.navigateUp() }
            }

            //积分排行页面
            composable(route = KeyNavigationRoute.INTEGRAL_RANK.route) {
                //系统颜色的状态栏
                StatsBarUtil().StatsBarColor(false)

                IntegralRankCompose(navController)

                BackHandler { navController.navigateUp() }
            }

            //我的收藏页面
            composable(route = KeyNavigationRoute.MY_COLLECT.route) {
                //系统颜色的状态栏
                StatsBarUtil().StatsBarColor(false)

                MyCollectCompose(navController)

                BackHandler { navController.navigateUp() }
            }

            //我分享的文章页面
            composable(route = KeyNavigationRoute.MY_SHARE_ARTICLES.route) {
                //系统颜色的状态栏
                StatsBarUtil().StatsBarColor(false)

                MyShareArticlesCompose(navController)

                BackHandler { navController.navigateUp() }
            }

            //设置页面
            composable(route = KeyNavigationRoute.SETTING.route) {
                //系统颜色的状态栏
                StatsBarUtil().StatsBarColor(false)

                SettingCompose(navController)

                BackHandler { navController.navigateUp() }
            }

            //H5页面
            composable(
                route = "${KeyNavigationRoute.WEBVIEW.route}?url={url}", arguments = listOf(
                    navArgument("url") { defaultValue = "https://www.wanandroid.com/" })
            ) { backStackEntry ->

                //系统颜色的状态栏
                StatsBarUtil().StatsBarColor(false)

                WebViewCompose(navController, backStackEntry.arguments?.getString("url") ?: "https://www.wanandroid.com")

                BackHandler { navController.navigateUp() }
            }


        }
    )
}

/**
 * 页面跳转关键类
 */
enum class KeyNavigationRoute(
    val route: String
) {
    //主页面
    MAIN("main"),

    //登录页面
    LOGIN("login"),

    //注册页面
    REGISTER("register"),

    //积分排行
    INTEGRAL_RANK("integral_rank"),

    //我的收藏
    MY_COLLECT("my_collect"),

    //我的文章
    MY_SHARE_ARTICLES("my_share_articles"),

    //设置
    SETTING("setting"),

    //H5
    WEBVIEW("webview")
}










