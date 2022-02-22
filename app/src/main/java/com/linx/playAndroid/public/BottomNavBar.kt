package com.linx.playAndroid.public

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.linx.common.baseData.Nav

//底部导航栏列表
val items = listOf(
    Nav.BottomNavScreen.HomeScreen,
    Nav.BottomNavScreen.ProjectScreen,
    Nav.BottomNavScreen.SquareScreen,
    Nav.BottomNavScreen.PublicNumScreen,
    Nav.BottomNavScreen.LearnScreen,
    Nav.BottomNavScreen.MineScreen
)

/**
 * 底部导航栏
 */
@Composable
fun BottomNavBar(
    bottomNavScreen: Nav.BottomNavScreen,
    navHostController: NavHostController
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary
    ) {
        items.forEach { bottomNavScreenItem: Nav.BottomNavScreen ->
            //记录动画
            val translationY = remember { androidx.compose.animation.core.Animatable(0F) }

            //开启线程执行动画
            LaunchedEffect(bottomNavScreen) {
                if (bottomNavScreenItem == bottomNavScreen)
                    translationY.animateTo(-4F)
                else translationY.animateTo(0F)
            }

            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(bottomNavScreenItem.id),
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
                label = { Text(stringResource(bottomNavScreenItem.resourceId)) },
                selected = bottomNavScreen == bottomNavScreenItem,
                onClick = {
                    //判断是否是当前的route,如果是就不做处理
                    if (bottomNavScreenItem == bottomNavScreen) {
                        return@BottomNavigationItem
                    }
                    //记录当前的Item
                    Nav.bottomNavRoute.value = bottomNavScreenItem

                    navHostController.navigate(bottomNavScreenItem.route) {
                        popUpTo(navHostController.graph.findStartDestination().id) {
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