package com.linx.wanandroid.navigation

import android.text.style.TtsSpan
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.linx.common.baseData.Nav
import com.linx.common.baseData.statusBarTitle
import com.linx.common.model.StatusBarTitleData
import com.linx.wanandroid.HomeCompose
import com.linx.wanandroid.ThemeCompose
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
            statusBarTitle.value.apply {
                AppBar(title, leftIcon, rightIcon, onLeftClick, onRightClick)
            }
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
        }
    )
}








