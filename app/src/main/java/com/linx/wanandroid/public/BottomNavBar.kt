package com.linx.wanandroid.public

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.linx.common.baseData.Nav

//底部导航栏列表
val items = listOf(
    Nav.BottomNavScreen.HomeScreen,
    Nav.BottomNavScreen.ProjectScreen,
    Nav.BottomNavScreen.SquareScreen,
    Nav.BottomNavScreen.PublicNumScreen,
    Nav.BottomNavScreen.MineScreen
)

/**
 * 底部导航栏
 */
@Composable
fun BottomNavBar(
    navController: NavHostController
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary
    ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()

        val currentDestination = navBackStackEntry?.destination

        items.forEach { bottomNavScreen: Nav.BottomNavScreen ->

            //记录动画
            val translationY = remember { androidx.compose.animation.core.Animatable(0F) }

            //开启线程执行动画
            LaunchedEffect(Nav.bottomNavRoute.value) {
                if (bottomNavScreen.route == Nav.bottomNavRoute.value.route)
                    translationY.animateTo(-4F)
                else translationY.animateTo(0F)
            }

            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(bottomNavScreen.id),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp).offset(
                            0.dp,
                            //上下偏移
                            translationY.value.dp
                        ),
                    )
                },
                //选中选项的颜色 (text\icon\波纹)
                selectedContentColor = MaterialTheme.colors.primary,
                //未选中选项的颜色 (text\icon\波纹)
                unselectedContentColor = MaterialTheme.colors.secondaryVariant,
                label = { Text(stringResource(bottomNavScreen.resourceId)) },
                selected = currentDestination?.hierarchy?.any { it.route == bottomNavScreen.route } == true,
                onClick = {
                    //判断是否是当前的route,如果是就不做处理
                    if (bottomNavScreen.route == Nav.bottomNavRoute.value.route) {
                        return@BottomNavigationItem
                    }
                    //记录当前的Item
                    Nav.bottomNavRoute.value = bottomNavScreen

                    navController.navigate(bottomNavScreen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        //避免重建
                        launchSingleTop = true
                        //重新选择以前选择的项目时，恢复状态
                        restoreState = true
                    }
                },
                modifier = Modifier.background(MaterialTheme.colors.background)
            )
        }
    }

}