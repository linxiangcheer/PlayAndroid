package com.linx.wanandroid.public

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
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
            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                label = { Text(stringResource(bottomNavScreen.resourceId)) },
                selected = currentDestination?.hierarchy?.any { it.route == bottomNavScreen.route } == true,
                onClick = {
                    //判断是否是当前的route,如果是就不做处理
                    if (Nav.bottomNavRoute.value.route == bottomNavScreen.route) {
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
                }
            )
        }
    }

}