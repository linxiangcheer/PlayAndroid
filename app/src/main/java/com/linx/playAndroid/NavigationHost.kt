package com.linx.playAndroid

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.linx.common.baseData.Nav
import com.linx.playAndroid.composable.*

/**
 * 内容 导航
 */
@Composable
fun NavigationHost(navController: NavHostController) {

    NavHost(
        navController,
        startDestination = KeyNavigationRoute.MAIN.route,
        builder = {

            //主页面
            navigation(route = KeyNavigationRoute.MAIN.route, startDestination = Nav.BottomNavScreen.HomeScreen.route) {
                composable(Nav.BottomNavScreen.HomeScreen.route) { HomeCompose(navController) }
                composable(Nav.BottomNavScreen.ProjectScreen.route) { ProjectCompose(navController) }
                composable(Nav.BottomNavScreen.SquareScreen.route) { SquareCompose(navController) }
                composable(Nav.BottomNavScreen.PublicNumScreen.route) { PublicNumCompose(navController) }
                composable(Nav.BottomNavScreen.MineScreen.route) { MineCompose(navController) }
            }

            composable(route = KeyNavigationRoute.LOGIN.route) { LoginCompose(navController) }

        }
    )
}

enum class KeyNavigationRoute(
    val route: String
) {
    //主页面
    MAIN("main"),
    //登录页面
    LOGIN("login"),
}








