package com.linx.playAndroid.widget

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * 设置状态栏颜色
 */
class StatsBarUtil {

    /**
     * [isTransparent] 是否透明
     * [statusBarColor] 状态栏颜色
     * [navigationBarColor] 导航栏颜色
     * [statusBarDarkIcons] 状态栏内容是否为深色
     * [navigationBarColorDarkIcons] 导航栏内容是否为深色
     */
    @Composable
    fun StatsBarColor(
        isTransparent: Boolean = false,
        statusBarColor: Color = if (isTransparent) Color.Transparent else MaterialTheme.colors.primary,
        navigationBarColor: Color = if (isTransparent) Color.Transparent else MaterialTheme.colors.background,
        statusBarDarkIcons: Boolean = statusBarColor.luminance() > 0.5f,
        navigationBarColorDarkIcons: Boolean = navigationBarColor.luminance() > 0.5f
        //如果要求使用深色图标但没有，将调用一个lambda来转换颜色,默认情况下是应用一个黑色框框
//        transformColorForLightContent: (Color) -> Color
    ) {
        rememberSystemUiController().apply {
            setStatusBarColor(statusBarColor, statusBarDarkIcons)
            setNavigationBarColor(navigationBarColor, navigationBarColorDarkIcons)
        }
    }

}