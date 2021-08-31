package com.linx.playAndroid.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.linx.common.baseData.Nav
import com.linx.playAndroid.NavigationHost
import com.linx.playAndroid.public.AppBar
import com.linx.playAndroid.public.BottomNavBar
import com.linx.playAndroid.viewModel.ProjectViewModel

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
            BottomNavBar(Nav.bottomNavRoute.value, navController)
        },
        //内容
        content = { paddingValues: PaddingValues ->
            NavigationHost(navController, paddingValues)

            OnTwoBackContent(navController)
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
//            val projectViewModel: ProjectViewModel = viewModel()
//
//            val projectTreeData = projectViewModel.projectTreeData.observeAsState()
//
//            //顶部指示器
//            projectTreeData.value?.let { projectTab(it, projectViewModel) }
//            AppBar("项目", Icons.Default.Face, Icons.Default.Search)
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

/**
 * 在主界面点击两次返回按钮,返回到手机桌面,再重新打开app,此时显示退出时的主界面
 */
@Composable
private fun OnTwoBackContent(navController: NavHostController) {
    if (Nav.twoBackFinishActivity) {
        LaunchedEffect(Unit) {
            navController.navigate(Nav.bottomNavRoute.value.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                //避免重建
                launchSingleTop = true
                //重新选择以前选择的项目时，恢复状态
                restoreState = true
            }
            Nav.twoBackFinishActivity = false
        }
    }
}