package com.linx.wanandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.viewmodel.compose.viewModel
import com.linx.wanandroid.public.Page
import com.linx.wanandroid.ui.theme.CustomThemeManager
import com.linx.wanandroid.viewModel.MainViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel: MainViewModel = viewModel()

            val typeState = viewModel.themeTypeState.value

            //状态栏
            window.statusBarColor = if (isSystemInDarkTheme()) {
                CustomThemeManager.getWrappedColor(typeState).darkColors.primary
            } else {
                CustomThemeManager.getWrappedColor(typeState).lightColors.primary
            }.toArgb()

            //底部导航栏
            window.navigationBarColor = if (isSystemInDarkTheme()) {
                CustomThemeManager.getWrappedColor(typeState).darkColors.primary
            } else {
                CustomThemeManager.getWrappedColor(typeState).lightColors.primary
            }.toArgb()

            //内容
            Page("你好", typeState, leftIcon = Icons.Default.Favorite, rightIcon = Icons.Default.Search) {
                MainCompose()
            }

        }
    }

}