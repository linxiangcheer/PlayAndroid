package com.linx.wanandroid.public

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.linx.wanandroid.ui.theme.CustomThemeManager
import com.linx.wanandroid.ui.theme.ThemeType

/**
 * 带标题栏的内容
 */
@Composable
fun Page(
    title: String,
    themeType: ThemeType,
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    leftIcon: ImageVector? = null,
    rightIcon: ImageVector? = null,
    onLeftClick: () -> Unit = {},
    onRightClick: () -> Unit = {},
    content: @Composable () -> Unit
) {

    CustomThemeManager.WanAndroidTheme(themeType, isDarkTheme) {
        Surface(
            color = MaterialTheme.colors.background
        ) {
            Column {
                AppBar(title, leftIcon, rightIcon, onLeftClick, onRightClick)

                content()
            }
        }
    }

}