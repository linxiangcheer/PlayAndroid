package com.linx.wanandroid.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.linx.common.baseData.Nav
import com.linx.wanandroid.composable.*
import com.linx.wanandroid.public.AppBar
import com.linx.wanandroid.public.BottomNavBar


/**
 * 主界面
 */
@Composable
fun MainCompose() {

    val navController = rememberNavController()

    Scaffold(
        contentColor = MaterialTheme.colors.background,
        //标题栏
        topBar = {
            MainTopABar(Nav.bottomNavRoute.value)
        },
        //底部导航栏
        bottomBar = {
            BottomNavBar(navController)
        },
        //内容
        content = { paddingValues: PaddingValues ->
            NavHost(navController, paddingValues)
        }
    )

}

/**
 * 内容 导航
 */
@Composable
private fun NavHost(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController,
        startDestination = Nav.BottomNavScreen.HomeScreen.route,
        Modifier.padding(paddingValues),
        builder = {
            composable(Nav.BottomNavScreen.HomeScreen.route) { HomeCompose(navController) }
            composable(Nav.BottomNavScreen.ProjectScreen.route) { ThemeCompose() }
            composable(Nav.BottomNavScreen.SquareScreen.route) { SquareCompose() }
            composable(Nav.BottomNavScreen.PublicNumScreen.route) { PublicNumCompose() }
            composable(Nav.BottomNavScreen.MineScreen.route) { MineCompose() }
        }
    )
}

/**
 * 主页面的标题栏
 */
@Composable
private fun MainTopABar(bottomNavScreen: Nav.BottomNavScreen) {
    when(bottomNavScreen) {
        Nav.BottomNavScreen.HomeScreen -> {
            AppBar("首页", rightIcon = Icons.Default.Search)
        }
        Nav.BottomNavScreen.ProjectScreen -> {
            AppBar("项目", Icons.Default.Face, Icons.Default.Search)
        }
        Nav.BottomNavScreen.SquareScreen -> {
            AppBar("广场", Icons.Default.Send)
        }
        Nav.BottomNavScreen.PublicNumScreen -> {
            AppBar("公众号", Icons.Default.ArrowForward)
        }
        Nav.BottomNavScreen.MineScreen -> {
            AppBar("我的", Icons.Default.Add)
        }
    }
}








