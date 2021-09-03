package com.linx.playAndroid

import android.content.Context
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.linx.common.baseData.Nav
import com.linx.common.widget.TwoBackFinish
import com.linx.common.widget.TwoBackFinishA
import com.linx.playAndroid.composable.*

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
            navigation(route = KeyNavigationRoute.MAIN.route, startDestination = Nav.BottomNavScreen.HomeScreen.route) {
                composable(Nav.BottomNavScreen.HomeScreen.route) {
                    HomeCompose(navController)
                    //点击两次返回才关闭app
                    BackHandler {
                        TwoBackFinishA().execute(context, onFinish)
                    }
                }
                composable(Nav.BottomNavScreen.ProjectScreen.route) {
                    ProjectCompose(navController)
                    //点击两次返回才关闭app
                    BackHandler {
                        TwoBackFinishA().execute(context, onFinish)
                    }
                }
                composable(Nav.BottomNavScreen.SquareScreen.route) {
                    SquareCompose(navController)
                    //点击两次返回才关闭app
                    BackHandler {
                        TwoBackFinishA().execute(context, onFinish)
                    }
                }
                composable(Nav.BottomNavScreen.PublicNumScreen.route) {
                    PublicNumCompose(navController)
                    //点击两次返回才关闭app
                    BackHandler {
                        TwoBackFinishA().execute(context, onFinish)
                    }
                }
                composable(Nav.BottomNavScreen.MineScreen.route) {
                    MineCompose(navController)
                    //点击两次返回才关闭app
                    BackHandler {
                        TwoBackFinishA().execute(context, onFinish)
                    }
                }
            }

            composable(route = KeyNavigationRoute.LOGIN.route) {
                LoginCompose(navController)
                BackHandler {
                    Log.d("xx", "主界面点击返回按钮")
                    navController.navigateUp()
                }
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
}










