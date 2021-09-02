package com.linx.playAndroid.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.linx.common.baseData.Nav
import com.linx.playAndroid.NavigationHost
import com.linx.playAndroid.model.ProjectTreeData
import com.linx.playAndroid.model.PublicNumChapterData
import com.linx.playAndroid.public.AppBar
import com.linx.playAndroid.public.BottomNavBar
import com.linx.playAndroid.viewModel.ProjectViewModel
import com.linx.playAndroid.viewModel.PublicNumViewModel

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
            MainTopBar(Nav.bottomNavRoute.value)
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
private fun MainTopBar(bottomNavScreen: Nav.BottomNavScreen) {
    when(bottomNavScreen) {
        //首页
        Nav.BottomNavScreen.HomeScreen -> {
            AppBar("首页", rightIcon = Icons.Default.Search)
        }
        //项目
        Nav.BottomNavScreen.ProjectScreen -> {

            val projectViewModel: ProjectViewModel = viewModel()

            //请求项目列表数据
            projectViewModel.getProjectTreeData()

            val projectTreeData = projectViewModel.projectTreeData.observeAsState()

            //顶部指示器
            ProjectTab(Nav.projectTopBarIndex, projectTreeData)
        }
        //广场
        Nav.BottomNavScreen.SquareScreen -> {
            //顶部指示器
            SquareTab(Nav.squareTopBarIndex)
        }
        //公众号
        Nav.BottomNavScreen.PublicNumScreen -> {

            val publicNumViewModel: PublicNumViewModel = viewModel()

            //请求公众号列表数据
            publicNumViewModel.getPublicNumChapterData()

            val publicNumChapterData = publicNumViewModel.publicNumChapter.observeAsState()

            //顶部指示器
            PublicNumTab(Nav.publicNumIndex, publicNumChapterData)

        }
        //我的
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

/**
 * 项目页面顶部的指示器
 * [projects]指示器下方的内容
 */
@Composable
private fun ProjectTab(
    projectTopBarIndex: MutableState<Int>,
    projectTreeData: State<List<ProjectTreeData>?>
) {

    if (projectTreeData.value == null) {
        Box(
            modifier = Modifier.background(MaterialTheme.colors.primary).fillMaxWidth()
                .height(54.dp)
        )
        return
    }

    ScrollableTabRow(
        selectedTabIndex = projectTopBarIndex.value,
        modifier = Modifier.fillMaxWidth().height(50.dp),
        //边缘padding
        edgePadding = 0.dp,
        backgroundColor = MaterialTheme.colors.primary
    ) {
        projectTreeData.value!!.forEachIndexed { index, item ->
            Tab(
                text = { Text(item.name ?: "") },
                selected = projectTopBarIndex.value == index,
                onClick = {
                    projectTopBarIndex.value = index
                }
            )
        }
    }
}

val squareTopBarList = listOf<String>("广场", "每日一问", "体系", "导航")
/**
 * 广场页面的TopBar
 */
@Composable
private fun SquareTab(squareTopBarIndex: MutableState<Int>) {

    //顶部指示器
    ScrollableTabRow(
        selectedTabIndex = squareTopBarIndex.value,
        modifier = Modifier.fillMaxWidth().height(50.dp),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        squareTopBarList.forEachIndexed { index, item ->
            Tab(
                text = { Text(item) },
                selected = index == squareTopBarIndex.value,
                onClick = {
                    squareTopBarIndex.value = index
                }
            )
        }
    }

}

/**
 * 公众号页面顶部的指示器
 * [projects]指示器下方的内容
 */
@Composable
private fun PublicNumTab(
    publicNumIndex: MutableState<Int>,
    publicNumChapterData: State<List<PublicNumChapterData>?>
) {

    if (publicNumChapterData.value == null) {
        Box(
            modifier = Modifier.background(MaterialTheme.colors.primary).fillMaxWidth()
                .height(54.dp)
        )
        return
    }

    ScrollableTabRow(
        selectedTabIndex = publicNumIndex.value,
        modifier = Modifier.fillMaxWidth().height(50.dp),
        //边缘padding
        edgePadding = 0.dp,
        backgroundColor = MaterialTheme.colors.primary
    ) {
        publicNumChapterData.value!!.forEachIndexed { index, item ->
            Tab(
                text = { Text(item.name ?: "") },
                selected = publicNumIndex.value == index,
                onClick = {
                    publicNumIndex.value = index
                }
            )
        }
    }
}